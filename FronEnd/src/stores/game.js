import { defineStore } from 'pinia';
import axios from 'axios';

export const useGameStore = defineStore({
  id: 'game',
  state: () => ({
    problems: [],
    correctAnswers: 0,
    wrongAnswers: 0, // 추가: 오답 갯수
    finalScore: 0,
    isModalOpen: false,
  }),
  actions: {
    async fetchProblems() {
      try {
        const response = await axios.get('http://localhost:8080/ansmoon/problem/list');
        this.problems = response.data;
      } catch (error) {
        console.error('문제를 불러오는 동안 오류가 발생했습니다:', error);
      }
    },
    async checkAnswer(index, answer) {
      try {
        const response = await axios.post('http://localhost:8080/ansmoon/problem/check', { index, answer });
        const isCorrect = response.data;
        if (isCorrect) {
          this.incrementCorrectAnswers();
          this.updateFinalScore(); // 정답을 맞추면 최종 점수를 업데이트합니다.
        }
        return isCorrect;
      } catch (error) {
        console.error('정답을 확인하는 동안 오류가 발생했습니다:', error);
        return false;
      }
    },
    async getCorrectCount() {
      try {
        const response = await axios.get('http://localhost:8080/ansmoon/problem/correctCount');
        return response.data;
      } catch (error) {
        console.error('정답 갯수를 가져오는 동안 오류가 발생했습니다:', error);
        return 0;
      }
    },
    async getFinalScore() {
      try {
        const response = await axios.get('http://localhost:8080/ansmoon/problem/finalScore');
        return response.data;
      } catch (error) {
        console.error('최종 점수를 가져오는 동안 오류가 발생했습니다:', error);
        return 0;
      }
    },
    async saveResult() {
      try {
        await axios.post('http://localhost:8080/ansmoon/problem/save');
      } catch (error) {
        console.error('결과를 저장하는 동안 오류가 발생했습니다:', error);
      }
    },
    incrementCorrectAnswers() {
      this.correctAnswers++;
    },
    incrementWrongAnswers() { // 추가: 오답 갯수 증가 함수
      this.wrongAnswers++;
    },
    updateFinalScore() {
      this.finalScore = this.correctAnswers * 10;
    },
    toggleModal() {
      this.isModalOpen = !this.isModalOpen;
    },
  },
});
