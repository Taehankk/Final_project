<template>
  <div>
    <h2>ansmoon's page</h2>
    <h4>...</h4>

    <h1>KAKAO TEST</h1>
    <button @click="getHint">Hint</button>
    <div>
      {{ kakaoStore.hint }}
    </div>
  </div>
</template>

<script setup>
import { useKakaoStore } from "@/stores/kakao";
import { ref } from "vue";
const kakaoStore = useKakaoStore();

const getHint = function () {
  kakaoStore.getHint();
  console.log("hint out");
};
</script>

<style scoped></style>
