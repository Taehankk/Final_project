<template>
  <div class="game-container">
    <div class="problem-title">
      <h3 v-if="currentProblem">
        문제 {{ store.currentIndex + 1 }} - {{ currentProblem.problemName }}
      </h3>
    </div>

    <!-- 이미지 표시 부분 -->
    <div class="image-container" v-if="currentProblemImageUrl">
      <img
        :src="currentProblemImageUrl"
        alt="사진 오류"
        class="center-image"
        :width="300"
        aspect-ratio="1"
        cover
      />
    </div>

    <div class="answer-input" v-if="!isGameFinished">
      <input
        ref="userAnswerInput"
        :value="userAnswer"
        type="text"
        placeholder="정답을 입력하세요"
        @input="updateUserAnswer"
        @keyup.enter="checkAnswer"
      />
      <button @click="checkAnswer">제출</button>
    </div>

    <!-- 현재 문제와 최종 문제 상태 -->
    <div class="game-status" v-if="!store.isModalOpen">
      <div class="status-item">
        <p class="status-label">진행도</p>
        <p class="status-value">
          {{ store.currentIndex + 1 }} / {{ store.problems.length }}
        </p>
      </div>
      <div class="status-item">
        <p class="status-label">현재 맞힌 문제 수</p>
        <p class="status-value">{{ store.correctAnswers }}</p>
      </div>
      <div class="status-item">
        <p class="status-label">현재 점수</p>
        <p class="status-value">{{ store.finalScore }}</p>
      </div>
      <div class="status-item">
        <p class="status-label">남은 시간</p>
        <p class="status-value">{{ formattedTimeLeft }}초</p>
      </div>
    </div>

    <!-- 시간과 시간 표시 바 -->
    <div class="timer" v-if="!store.isModalOpen">
      <p>남은 시간: {{ formattedTimeLeft }}초</p>
      <div class="timebar-container">
        <transition name="timebar-transition">
          <div class="timebar" :style="{ width: timeBarWidth }"></div>
        </transition>
      </div>
    </div>

    <!-- Hint 버튼 -->
    <div class="hint-section">
      <button
        class="hint-button"
        @click="getHint"
        v-if="hintBool && !isGameFinished"
      >
        Hint 
      </button>
      <span class="hint-result">{{ store.hint }}</span>
    </div>

    <!-- 모달 -->
    <div class="modal" v-if="store.isModalOpen">
      <div class="modal-content">
        <!-- x 버튼을 클릭했을 때 closeModalWithoutSaving 함수 호출 -->
        <span class="close" @click="closeModalWithoutSaving">&times;</span>
        <h2 class="modal-title">게임 결과</h2>
        <div class="result-details">
          <p><strong>맞힌 문제 수:</strong> {{ store.correctAnswers }}</p>
          <p><strong>최종 점수:</strong> {{ store.finalScore }}</p>
        </div>
        <div class="modal-buttons">
          <button @click="saveResult" class="save-button">결과 저장</button>
          <!-- 저장하지 않고 종료 버튼을 눌렀을 때 closeModalWithoutSaving 함수 호출 -->
          <button @click="closeModalWithoutSaving" class="close-button">
            저장하지 않고 종료
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch, onBeforeUnmount } from "vue";
import { useRoute } from "vue-router";
import { useRouter } from "vue-router";
import { useGameStore } from "@/stores/game";
import { useKakaoStore } from "@/stores/kakao";

const store = useGameStore();
const kakaoStore = useKakaoStore();
const router = useRouter();
const userAnswer = ref("");
const userAnswerInput = ref(null); // 입력 필드를 참조하는 ref
const route = useRoute();

const currentProblem = computed(() => store.problems[store.currentIndex]);
const hintCnt = ref(0);
const hintBool = ref(true);
const isGameFinished = ref(false);
const url = ref("");

const currentProblemImageUrl = computed(() => {
  if (currentProblem.value && currentProblem.value.problemId) {
    // console.log(currentProblem.value.problemAnswer);
    const { category, problemId } = route.params; // category와 problemId 추출
    url.value = `/src/assets/${category}/${currentProblem.value.problemId}.jpg`; // 이미지 경로를 구성하여 반환합니다.
    return url.value;
  }
  return null; // problemId가 없는 경우 null을 반환합니다.
});


const timeBarWidth = computed(() => `${(store.timeLeft / 10) * 100}%`);

const formattedTimeLeft = computed(() => {
  return store.timeLeft.toFixed(2);
});

const updateUserAnswer = (event) => {
  userAnswer.value = event.target.value;
};

const checkAnswer = async () => {
  if (userAnswer.value.trim() !== "") {
    const isCorrect = await store.checkAnswer(userAnswer.value.trim());
    if (isCorrect) {
      alert("정답입니다!");
    } else if (!isCorrect) {
      alert(`틀렸습니다! 정답은 ${currentProblem.value.problemAnswer}입니다.`);
    }
    userAnswer.value = "";
    userAnswerInput.value.focus();
    store.nextProblem();
  } else {
    alert("정답을 입력하세요!");
    userAnswerInput.value.focus();
  }
};

const getHint = async () => {
  if (!sessionStorage.getItem("hintCheck")) {
    sessionStorage.setItem("hintCheck", true);
    hintCnt.value++;
    if (hintCnt.value >= 2) {
      hintBool.value = false;
    }
    await store.getHint(store.currentIndex);
  }
};

const closeModalWithoutSaving = () => {
  store.isModalOpen = false;
  router.push({ name: "home" });
};

const saveResult = async () => {
  await store.saveResult(route.params.category);
  store.isModalOpen = false;
  router.push({ name: "home" });
};

router.afterEach((to) => {
  if (to.name === "home") {
    clearInterval(timer);
    store.correctAnswers = 0;
    store.wrongAnswers = 0;
    store.finalScore = 0;
  }
});

onMounted(() => {
  store.fetchProblems(route.params.category);
  store.resetGame();

  setTimeout(() => {
    userAnswerInput.value.focus();
  }, 20);

  let timer = null; // 타이머 변수를 전역으로 선언합니다.

  const startTimer = () => {
    timer = setInterval(() => {
      if (store.timeLeft > 0) {
        store.timeLeft -= 0.01;
      } else {
        alert(`시간초과! 정답은 ${currentProblem.value.problemAnswer}입니다.`);
        store.nextProblem();
        userAnswer.value = "";
        userAnswerInput.value.focus();
      }
    }, 10);
  };

  const stopTimer = () => {
    clearInterval(timer); // 타이머를 정지합니다.
  };

  // 문제를 모두 푼 경우에만 타이머를 시작합니다.
  watch(
    () => store.currentIndex,
    (newIndex, oldIndex) => {
      if (newIndex !== oldIndex && newIndex === store.problems.length) {
        stopTimer(); // 모든 문제를 푼 경우 타이머를 정지합니다.
        isGameFinished.value = true;
      }
    }
  );

  startTimer(); // 타이머를 시작합니다.

  onBeforeUnmount(() => {
    stopTimer(); // 컴포넌트가 소멸될 때 타이머를 정지합니다.
  });

  if (sessionStorage.getItem("hintCheck")) {
    sessionStorage.removeItem("hintCheck");
  }
});
</script>

<style scoped>
/* 글로벌 스타일 */
html,
body {
  font-family: Arial, sans-serif; /* 전체 페이지의 기본 폰트 설정 */
  margin: 0;
  padding: 0;
}

/* 게임 컨테이너 스타일 */
.game-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background-color: rgb(223, 236, 255);
}

/* 문제 제목 스타일 */
.problem-title {
  margin-top: 20px;
  font-size: 1.5rem;
  font-weight: bold;
  text-align: center;
  min-height: 3rem; /* 두 줄 정도의 공간 확보 */
}


/* 이미지 컨테이너 스타일 */
.image-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  max-width: 500px;
  height: 300px;
  overflow: hidden; /* 이미지를 자르기 위해 필요 */
  margin-top: 20px;
}

.hint-section {
  display: inline-flex;
  align-items: center;
}
/* 힌트 버튼 스타일 */
.hint-button {
  margin-top: 20px; /* timebar 아래 여백 조정 */
  width: 200px;
  height: 100px;
  border-radius: 5px;
  background-color: #4caf50;
  border: none;
  color: white;
  font-size: 3rem; /* 문자 크기 조정 */
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
}

.hint-result {
  font-size: 1rem;
}

/* 사용자 입력 부분 스타일 */
.answer-input {
  margin-top: 20px;
  display: flex;
  justify-content: center; /* 입력 필드를 가운데로 정렬 */
  align-items: center;
}

.answer-input input {
  padding: 10px;
  font-size: 1rem;
  border: 2px solid #ccc;
  border-radius: 5px;
  width: 300px;
}

.answer-input button {
  padding: 10px 20px;
  font-size: 1rem;
  border: none;
  border-radius: 5px;
  background-color: #4caf50;
  color: white;
  cursor: pointer;
  margin-left: 10px;
}

/* 현재 문제와 최종 문제 상태 스타일 */
.game-status {
  margin-top: 20px;
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 100%;
  max-width: 600px;
  border: 2px solid #ccc; /* 겉테두리 실선 추가 */
  padding: 20px; /* 내부 여백 추가 */
  border-radius: 10px; /* 둥근 테두리 추가 */
}

.status-item {
  text-align: center; /* 내용 가운데 정렬 */
}

.status-label {
  font-size: 1.2rem; /* 라벨 폰트 크기 증가 */
  color: #666;
}

.status-value {
  font-size: 1.5rem; /* 값 폰트 크기 증가 */
  font-weight: bold;
  color: #333;
}

.timer {
  margin-top: 20px;
  text-align: center;
  width: 100%;
}

.timer p {
  font-size: 1.2rem; /* 폰트 크기 증가 */
  font-weight: bold;
}

.timebar-container {
  width: 100%;
  height: 30px;
  background-color: #ddd;
  margin-top: 10px;
  position: relative;
}

.timebar {
  height: 100%;
  background-color: #4caf50;
  transition: width 1s ease;
  position: absolute;
  top: 0;
  left: 0;
  border-radius: 5px; /* 둥근 테두리 추가 */
}

/* 모달 스타일 */
.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 20px;
  border-radius: 10px; /* 둥근 테두리 추가 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 추가 */
  z-index: 1000;
}

.modal-content {
  width: 400px; /* 모달 너비 조정 */
}

.close {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 1.5rem;
  cursor: pointer;
  color: #aaa;
}

.close:hover {
  color: #666;
}

.modal-title {
  margin-bottom: 20px;
  font-size: 1.5rem;
}

.result-details {
  margin-bottom: 20px;
}

.result-details p {
  font-size: 1.2rem;
  margin-bottom: 10px;
}

.modal-buttons {
  display: flex;
  justify-content: center;
  align-items: center;
}

.save-button,
.close-button {
  padding: 10px 20px;
  margin: 0 10px;
  font-size: 1rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.save-button {
  background-color: #4caf50;
  color: white;
}

.close-button {
  background-color: #f44336;
  color: white;
}

.save-button:hover,
.close-button:hover {
  opacity: 0.8; /* 버튼에 호버 효과 추가 */
}
</style>
