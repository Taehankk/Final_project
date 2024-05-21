<template>
  <div class="game-container">
    <template v-if="currentProblem">
      <h1>
        문제 {{ store.currentIndex + 1 }} - {{ currentProblem.problemName }}
      </h1>

      <!-- 이미지 표시 부분 -->
      <img
        :src="currentProblemImageUrl"
        alt="Problem Image"
        v-if="currentProblemImageUrl"
      />

      <!-- Hint Button -->
      <div class="hint">
        <button @click="getHint" v-if="hintBool">Hint</button>
        <div>
          {{ store.hint }}
        </div>
      </div>

      <div class="answer-input">
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
      <div class="problem-status" v-if="!store.isModalOpen">
        <p>
          진행도: {{ store.currentIndex + 1 }} / {{ store.problems.length }}
        </p>
      </div>

      <!-- 현재 맞힌 갯수와 점수 표시 -->
      <div class="result-info" v-if="!store.isModalOpen">
        <p>현재 맞힌 문제 수: {{ store.correctAnswers }}</p>
        <p>현재 점수: {{ store.finalScore }}</p>
      </div>

      <!-- 시간과 시간 표시 바 -->
      <div class="timer" v-if="!store.isModalOpen">
        <p>남은 시간: {{ store.timeLeft }}초</p>
        <div class="timebar-container">
          <transition name="timebar-transition">
            <div class="timebar" :style="{ width: timeBarWidth }"></div>
          </transition>
        </div>
      </div>
    </template>

    <div class="modal" v-if="store.isModalOpen">
      <div class="modal-content">
        <span class="close" @click="closeModal">&times;</span>
        <h2>게임 결과</h2>
        <p>맞힌 문제 수: {{ store.correctAnswers }}</p>
        <p>최종 점수: {{ store.finalScore }}</p>
        <div class="modal-buttons">
          <button @click="saveResult" class="save-button">결과 저장</button>
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
import { useRouter } from "vue-router";
import { useGameStore } from "@/stores/game";

const store = useGameStore();
const router = useRouter();
const userAnswer = ref("");
const userAnswerInput = ref(null); // 입력 필드를 참조하는 ref

const currentProblem = computed(() => store.problems[store.currentIndex]);
const hintCnt = ref(0);
const hintBool = ref(true);

const currentProblemImageUrl = computed(() => {
  if (currentProblem.value && currentProblem.value.image) {
    return require(`@/assets/images/${currentProblem.value.image}`);
  }
});

const timeBarWidth = computed(() => `${(store.timeLeft / 10) * 100}%`);

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
    console.log(hintCnt.value);
    if (hintCnt.value >= 2) {
      hintBool.value = false;
    }
    await store.getHint(currentIndex.value);
    console.log("hint out");
  }
};

const closeModalWithoutSaving = () => {
  store.isModalOpen = false;
  router.push({ name: "home" });
};

const saveResult = async () => {
  await store.saveResult();
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
  store.fetchProblems();
  store.resetGame();

  setTimeout(() => {
    userAnswerInput.value.focus();
  }, 20);

  let timer = null; // 타이머 변수를 전역으로 선언합니다.

  const startTimer = () => {
    timer = setInterval(() => {
      if (store.timeLeft > 0) {
        store.timeLeft--;
      } else {
        alert(`시간초과! 정답은 ${currentProblem.value.problemAnswer}입니다.`);
        store.nextProblem();
        userAnswer.value = "";
        userAnswerInput.value.focus();
      }
    }, 1000);
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
      }
    }
  );

  startTimer(); // 타이머를 시작합니다.

  onBeforeUnmount(() => {
    stopTimer(); // 컴포넌트가 소멸될 때 타이머를 정지합니다.
  });
});
</script>

<style scoped>
.game-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.answer-input {
  margin-top: 20px;
}

.problem-status,
.result-info,
.timer {
  margin-top: 20px;
}

.timebar-container {
  width: 100%;
  height: 10px;
  background-color: #ddd;
}

.timebar {
  height: 100%;
  background-color: #4caf50;
}

.timebar-transition-enter-active,
.timebar-transition-leave-active {
  transition: width 1s ease;
}

.timebar-transition-enter,
.timebar-transition-leave-to {
  width: 0;
}

.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 20px;
  border: 1px solid #ccc;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  z-index: 1000;
}

.modal-content {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.modal-buttons {
  margin-top: 20px;
  display: flex;
  gap: 10px;
}

.save-button,
.close-button {
  padding: 10px 20px;
  border: none;
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
</style>
