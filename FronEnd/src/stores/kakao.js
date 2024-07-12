import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
const apiKey = import.meta.env.VITE_KAKAO_API_KEY;
const url = "https://api.kakaobrain.com/v1/inference/kogpt/generation";

export const useKakaoStore = defineStore("kakao", () => {
  const question =
    "택견'은 어느 나라 고유의 전통 무예일까요? 에 대한 힌트를 줘";
  const hint = ref("");

  const getHint = function () {
    axios
      .post(
        "/api/v1/inference/kogpt/generation",
        {
          prompt: question,
          max_tokens: 50,
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
        console.log(response.data.generations[0].text);
        hint.value = response.data.generations[0].text.split(".")[1];
      });
  };

  return { hint, getHint };
});
