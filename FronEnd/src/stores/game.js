import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
const apiKey = import.meta.env.VITE_GPT_API_KEY;
const question = ref("");
const hint = ref("");
export const useGameStore = defineStore({
  id: "game",
  state: () => ({
    problems: [],
    correctAnswers: 0,
    wrongAnswers: 0, // 추가: 오답 갯수
    finalScore: 0,
    isModalOpen: false,
    question,
    hint,
  }),
  actions: {
    async fetchProblems() {
      try {
        const response = await axios.get(
          "http://localhost:8080/ansmoon/problem/list"
        );
        this.problems = response.data;
      } catch (error) {
        console.error("문제를 불러오는 동안 오류가 발생했습니다:", error);
      }
    },
    async checkAnswer(index, answer) {
      try {
        const response = await axios.post(
          "http://localhost:8080/ansmoon/problem/check",
          { index, answer }
        );
        const isCorrect = response.data;
        if (isCorrect) {
          this.incrementCorrectAnswers();
          this.updateFinalScore(); // 정답을 맞추면 최종 점수를 업데이트합니다.
        }
        return isCorrect;
      } catch (error) {
        console.error("정답을 확인하는 동안 오류가 발생했습니다:", error);
        return false;
      }
    },
    async getCorrectCount() {
      try {
        const response = await axios.get(
          "http://localhost:8080/ansmoon/problem/correctCount"
        );
        return response.data;
      } catch (error) {
        console.error("정답 갯수를 가져오는 동안 오류가 발생했습니다:", error);
        return 0;
      }
    },
    async getFinalScore() {
      try {
        const response = await axios.get(
          "http://localhost:8080/ansmoon/problem/finalScore"
        );
        return response.data;
      } catch (error) {
        console.error("최종 점수를 가져오는 동안 오류가 발생했습니다:", error);
        return 0;
      }
    },
    async saveResult() {
      try {
        await axios.post("http://localhost:8080/ansmoon/problem/save");
      } catch (error) {
        console.error("결과를 저장하는 동안 오류가 발생했습니다:", error);
      }
    },
    incrementCorrectAnswers() {
      this.correctAnswers++;
    },
    incrementWrongAnswers() {
      // 추가: 오답 갯수 증가 함수
      this.wrongAnswers++;
    },
    updateFinalScore() {
      this.finalScore = this.correctAnswers * 10;
    },
    toggleModal() {
      this.isModalOpen = !this.isModalOpen;
    },
    async getHint(currentIndex) {
      console.log(currentIndex);
      console.log(this.problems[currentIndex].problemAnswer);
      await axios
        .post(
          "/gpt/v1/chat/completions",
          {
            model: "gpt-3.5-turbo",
            messages: [
              {
                role: "user",
                content:
                  this.problems[currentIndex].problemAnswer +
                  "가 정답인 퀴즈에 대한 힌트를 한 줄로 알려줘, 정답은 힌트에 들어가면 안 돼",
              },
            ],
            max_tokens: 100,
          },
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${apiKey}`,
            },
          }
        )
        .then((response) => {
          console.log(response.data.choices[0].message.content);
          this.hint = response.data.choices[0].message.content;
        });
    },
  },
});