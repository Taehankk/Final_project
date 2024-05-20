<template>
    <div>
      <div>
        <h4>사용자 랭킹</h4>
        <label>정렬 기준 : </label>
        <select v-model="searchInfo.orderSt">
          <option value="score">점수</option>
          <option value="solveDate">달성일</option>
        </select>
        <label>정렬 방향 : </label>
        <select
          v-if="searchInfo.orderSt === 'score'"
          v-model="searchInfo.orderByDir"
        >
          <option value="asc">오름차순</option>
          <option value="desc">내림차순</option>
        </select>
        <select
          v-show="searchInfo.orderSt === 'solveDate'"
          v-model="searchInfo.orderByDir"
        >
          <option value="asc">과거순</option>
          <option value="desc">최근순</option>
        </select>
      </div>
      <div>
        <label>검색 : </label>
        <select v-model="searchInfo.searchItem">
          <option value="none" disabled selected hidden>검색</option>
          <option value="nickName">닉네임</option>
          <option value="score">점수</option>
        </select>
        <input
          type="text"
          v-if="searchInfo.searchItem !== 'score'"
          placeholder="검색어를 입력하세요"
          v-model="searchInfo.searchValue"
          @keyup.enter="searchList"
        />
        <input
          type="number"
          v-if="searchInfo.searchItem === 'score'"
          placeholder="검색어를 입력하세요"
          v-model="searchInfo.searchValue"
          @keyup.enter="searchList"
        />
        <button @click="searchList">검색</button>
        <button @click="resetSearch">초기화</button>
      </div>
      <hr />
      <table>
        <tr>
          <th>이름</th>
          <th>ID</th>
          <th>닉네임</th>
          <th>점수</th>
          <th>달성일</th>
        </tr>
        <tr v-for="data in rankStore.dataList" :key="data.id">
          <td>{{ data.userName }}</td>
          <td>{{ data.userId }}</td>
          <td>{{ data.nickName }}</td>
          <td>{{ data.score }}</td>
          <td>{{ data.solveDate }}</td>
        </tr>
      </table>
    </div>
  </template>
  
  <script setup>
  import { useRankStore } from "@/stores/rank";
  import { ref, onMounted, watch } from "vue";
  import { useRoute, useRouter } from "vue-router";
  
  const router = useRouter();
  
  const rankStore = useRankStore();
  
  const searchInfo = ref({
    searchItem: "none",
    searchValue: "",
    orderSt: "score",
    orderByDir: "desc",
  });
  
  watch(
    () => searchInfo.value.orderSt,
    (nv, ov) => {
      if (nv !== ov) {
        rankStore.searchList(searchInfo.value);
      }
    }
  );
  
  watch(
    () => searchInfo.value.orderByDir,
    (nv, ov) => {
      if (nv !== ov) {
        rankStore.searchList(searchInfo.value);
      }
    }
  );
  
  const searchList = function () {
    console.log(searchInfo.value.searchItem === "none");
    if (searchInfo.value.searchItem === "none") {
      console.log(searchInfo.value.searchValue === "");
      if (searchInfo.value.searchValue !== "") {
        alert("검색기준을 입력하세요");
      } else {
        rankStore.searchList(searchInfo.value);
      }
    } else {
      if (searchInfo.value.searchValue === "") {
        alert("검색어를 입력하세요");
      } else {
        rankStore.searchList(searchInfo.value);
      }
    }
  };
  
  const resetSearch = function () {
    searchInfo.value.searchItem = "none";
    searchInfo.value.searchValue = "";
    rankStore.searchList(searchInfo.value);
  };
  
  onMounted(() => {
    console.log(searchInfo.value);
    rankStore.searchList(searchInfo.value);
  });
  </script>
  
  <style scoped>
  button,
  input,
  select {
    margin-right: 3px;
    border: 1px solid black;
  }
  </style>
  