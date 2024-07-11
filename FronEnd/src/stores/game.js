import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

// const apiKey = import.meta.env.VITE_GPT_API_KEY;
const apiKey = import.meta.env.VITE_KAKAO_API_KEY;
const question = ref("");
const hint = ref("");

const REST_PROBLEM_API = `http://localhost:8080/ansmoon/problem`;

export const useGameStore = defineStore({
  id: "game",
  state: () => ({
    problems: [],
    currentIndex: 0,
    correctAnswers: 0,
    wrongAnswers: 0,
    finalScore: 0,
    isModalOpen: false,
    question,
    hint,
    timeLeft: 10,
  }),
  actions: {
    resetGame() {
      this.problems = [];
      this.currentIndex = 0;
      this.correctAnswers = 0;
      this.wrongAnswers = 0;
      this.finalScore = 0;
      this.isModalOpen = false;
      this.question = "";
      this.hint = "";
      this.timeLeft = 10;
    },
    async fetchProblems(category) {
      try {
        const response = await axios.get(`${REST_PROBLEM_API}/list?category=${category}`);
        this.problems = response.data;
      } catch (error) {
        console.error("문제를 불러오는 동안 오류가 발생했습니다:", error);
      }
    },
    async checkAnswer(userAnswer) {
      try {
        const response = await axios.post(`${REST_PROBLEM_API}/check`, null, {
          params: { index: this.currentIndex, answer: userAnswer },
        });
        const isCorrect = response.data;

        if (isCorrect) {
          this.correctAnswers++;
          this.updateFinalScore();
        } else {
          this.wrongAnswers++;
        }
        return isCorrect;
      } catch (error) {
        console.error("정답을 확인하는 동안 오류가 발생했습니다:", error);
        return false;
      }
    },
    updateFinalScore() {
      this.finalScore = this.correctAnswers * 10;
    },
    async saveResult(category) {
      try {
        await axios.post(`${REST_PROBLEM_API}/save`, null, {
          params: {
            category: category,
            nickName: sessionStorage.getItem("user"),
            score: this.finalScore,
          },
          withCredentials: true,
        });
        alert("저장 성공!");
      } catch (error) {
        alert("결과 저장 실패했습니다. 뭔가 오류가 있나 봅니다.");
      }
    },

    async getHint(currentIndex) {
      // await axios
      //   .post(
      //     "/gpt/v1/chat/completions",
      //     {
      //       model: "gpt-3.5-turbo",
      //       messages: [
      //         {
      //           role: "user",
      //           content:
                  // this.problems[currentIndex].problemAnswer +
                  // "가 정답인 퀴즈에 대한 힌트를 한 줄로 알려줘, 정답은 힌트에 들어가면 안 돼",
      //         },
      //       ],
      //       max_tokens: 100,
      //     },
      //     {
      //       headers: {
      //         "Content-Type": "application/json",
      //         Authorization: `Bearer ${apiKey}`,
      //       },
      //     }
      //   )
      //   .then((response) => {
      //     console.log(response.data.choices[0].message.content);
      //     this.hint = response.data.choices[0].message.content;
      //   });

      axios
      .post(
        "/api/v1/inference/kogpt/generation",
        {
          prompt: 
          this.problems[currentIndex].problemAnswer,
          max_tokens: 100,
          temperature: 0.01,
          n: 1,
        },
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `KakaoAK ${apiKey}`,
          },
        }
      )
      .then((response) => {
        // console.log(response.data.generations[0].text);
        // this.hint = response.data.generations[0].text.split(".")[1];
        this.hint = response.data.generations[0].text;
      });
    },

    nextProblem() {
      this.currentIndex++;
      // console.log(this.currentIndex + " " + this.problems.length + " " + this.isModalOpen);
      this.timeLeft = 10; // reset timer for the next problem
      if (this.currentIndex >= this.problems.length) {
        this.isModalOpen = true;
      }
      sessionStorage.removeItem("hintCheck");
      this.hint="";
    },
  },
});