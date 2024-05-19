<template>
  <div class="game-container">
    <template v-if="currentProblem">
      <h1>문제 {{ currentIndex + 1 }} - {{ currentProblem.problemName }}</h1>

      <!-- 이미지 표시 부분 -->
      <img :src="currentProblemImageUrl" alt="Problem Image" v-if="currentProblemImageUrl" />

      <div class="answer-input">
        <input v-model="userAnswer" type="text" placeholder="정답을 입력하세요" @keyup.enter="checkAnswer" />
        <button @click="checkAnswer">제출</button>
      </div>

      <!-- 현재 문제와 최종 문제 상태 -->
      <div class="problem-status" v-if="!showModal">
        <p>진행도: {{ currentIndex + 1 }} / {{ store.problems.length }}</p>
      </div>

      <!-- 현재 맞힌 갯수와 점수 표시 -->
      <div class="result-info" v-if="!showModal">
        <p>현재 맞힌 문제 수: {{ store.correctAnswers }}</p>
        <p>현재 점수: {{ store.finalScore }}</p>
      </div>

      <!-- 시간과 시간 표시 바 -->
      <div class="timer" v-if="!showModal">
        <p>남은 시간: {{ timeLeft }}초</p>
        <div class="timebar-container">
          <transition name="timebar-transition">
            <div class="timebar" :style="{ width: timeBarWidth }"></div>
          </transition>
        </div>
      </div>
    </template>

    <div class="modal" v-if="showModal">
      <div class="modal-content">
        <span class="close" @click="closeModal">&times;</span>
        <h2>게임 결과</h2>
        <p>맞힌 문제 수: {{ store.correctAnswers }}</p>
        <p>최종 점수: {{ store.finalScore }}</p>
        <div class="modal-buttons">
          <button @click="saveResult" class="save-button">결과 저장</button>
          <button @click="closeModalWithoutSaving" class="close-button">저장하지 않고 종료</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch, onUnmounted } from 'vue';
import { useRouter } from 'vue-router';
import { useGameStore } from '@/stores/game';
import axios from 'axios';

const store = useGameStore();
const router = useRouter();
const currentIndex = ref(0);
const userAnswer = ref('');
const currentProblem = computed(() => store.problems[currentIndex.value]);
const currentProblemImageUrl = computed(() => {
  if (currentProblem.value && currentProblem.value.image) {
    return require(`@/assets/images/${currentProblem.value.image}`);
  }
});

let timerInterval = null;
let timeLeft = ref(0); // 제한 시간 설정
const timeBarWidth = computed(() => `${(timeLeft.value / 10) * 100}%`);

const nextProblem = () => {
  userAnswer.value = ''; // 입력한 답 초기화
  resetTimer(); // 타이머 초기화
  currentIndex.value++;
};


const checkAnswer = async () => {
  if (currentProblem.value && userAnswer.value.trim() !== '') {
    const problemAnswer = currentProblem.value.problemAnswer;
    const userAnswerLowerCase = userAnswer.value.toLowerCase();
    if (userAnswerLowerCase === problemAnswer.toLowerCase()) {
      alert('정답입니다!');
      await store.incrementCorrectAnswers();
      await store.updateFinalScore();
      nextProblem();
    } else {
      alert(`틀렸습니다! 정답은 ${problemAnswer}입니다.`);
      await store.incrementWrongAnswers();
      nextProblem();
    }
  } else {
    alert('정답을 입력하세요!');
  }
};

const startTimer = () => {
  // 현재 페이지가 "home" 페이지가 아닌 경우에만 타이머를 시작합니다.
  if (router.currentRoute.value.name !== 'home' && !timerInterval) {
    timeLeft.value = 3; // 초기화된 시간 설정 부분
    timerInterval = setInterval(() => {
      if (timeLeft.value > 0) {
        timeLeft.value--;
      } else {
        clearInterval(timerInterval);
        if (currentProblem.value) {
          alert(`시간초과! 정답은 ${currentProblem.value.problemAnswer}입니다.`);
        } else {
          alert('시간초과!');
        }
        nextProblem();
      }
    }, 1000);
  }
};

router.afterEach((to, from) => {
  if (to.name === 'home') {
    clearInterval(timerInterval);
    store.correctAnswers = 0;
    store.wrongAnswers = 0;
    store.finalScore = 0;
  }
});

const resetTimer = () => {
  clearInterval(timerInterval);
  timerInterval = null; // 타이머가 중복으로 돌아가는 것을 방지하기 위해 null로 설정
  startTimer();
};

onMounted(() => {
  store.fetchProblems();
  startTimer();
});

const showModal = ref(false);

const closeModal = () => {
  showModal.value = false;
  router.push({ name: 'home' });
};

const closeModalWithoutSaving = () => {
  showModal.value = false;
  router.push({ name: 'home' });
};

axios.defaults.withCredentials = true;

const saveResult = async () => {
  try {
    const response = await axios.post(
      'http://localhost:8080/ansmoon/problem/save',
      null, // 요청 본문을 null로 설정
      {
        params: {
          score: store.finalScore // 쿼리 파라미터로 score 전달
        },
        withCredentials: true // 세션 정보를 함께 전송
      }
    );

    if (response.status === 200) {
      alert('결과가 성공적으로 저장되었습니다!');
      showModal.value =  false;
      router.push({ name: 'home' });
    } else {
      alert('결과 저장에 실패했습니다. 다시 시도해주세요.');
    }
  } catch (error) {
    console.error('결과 저장 중 오류 발생:', error);
    alert('결과 저장 중 오류가 발생했습니다. 다시 시도해주세요.');
  }
};

watch(() => currentIndex.value, (newValue) => {
  if (newValue === store.problems.length) {
    showModal.value = true;
    clearInterval(timerInterval);
  }
});

// watch(userAnswer, (newValue, oldValue) => {
//   if (newValue !== oldValue && newValue !== '') {
//     // 입력 값이 변경되고 비어 있지 않은 경우에만 초기화
//     userAnswer.value = ''; // 입력란 초기화
//   }
// });

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

.problem-status, .result-info, .timer {
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

.timebar-transition-enter-active, .timebar-transition-leave-active {
  transition: width 1s ease;
}

.timebar-transition-enter, .timebar-transition-leave-to {
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

.save-button, .close-button {
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
