<template>
  <div class="ranking-board">
    <div class="search-bar">
      <h1>RANKING BOARD</h1>
      <br>
      <br>
      <label>정렬 기준 : </label>
      <select v-model="searchInfo.orderSt">
        <option value="score">점수</option>
        <option value="solveDate">달성일</option>
      </select>
      <label>정렬 방향 : </label>
      <select v-if="searchInfo.orderSt === 'score'" v-model="searchInfo.orderByDir">
        <option value="asc">오름차순</option>
        <option value="desc">내림차순</option>
      </select>
      <select v-show="searchInfo.orderSt === 'solveDate'" v-model="searchInfo.orderByDir">
        <option value="asc">과거순</option>
        <option value="desc">최근순</option>
      </select>
      <label>검색 : </label>
      <select v-model="searchInfo.searchItem">
        <option value="none" disabled selected hidden>검색기준</option>
        <option value="nickName">닉네임</option>
        <option value="score">점수</option>
      </select>
      <input
        v-if="searchInfo.searchItem !== 'score'"
        type="text"
        placeholder="검색어를 입력하다"
        v-model="searchInfo.searchValue"
        @keyup.enter="searchList"
      />
      <input
        v-if="searchInfo.searchItem === 'score'"
        type="number"
        placeholder="검색어를 입력하다"
        v-model="searchInfo.searchValue"
        @keyup.enter="searchList"
      />
      <button @click="searchList">검색</button>
      <button @click="resetSearch">초기화</button>
    </div>
    <hr />

    <v-card>
      <v-tabs v-model="tab">
        <v-tab value="basic">EXERCISE-mix</v-tab>
        <v-tab value="kbo">KBO</v-tab>
        <v-tab value="kleague">K-LEAGUE</v-tab>
        <v-tab value="champs">해외축구</v-tab>
        <v-tab value="esports">E-SPORTS</v-tab>
        <v-tab value="forever">4반</v-tab>
      </v-tabs>

      <v-card-text>
        <v-tabs-items v-model="tab">
          <v-tab-item value="kleague">
            <table class="ranking-table">
              <tr>
                <th>이름</th>
                <th>ID</th>
                <th>닉네임</th>
                <th>카테고리</th>
                <th>점수</th>
                <th>달성일</th>
              </tr>
              <tr v-for="data in rankStore.dataList" :key="data.id">
                <td>{{ data.userName }}</td>
                <td>{{ data.userId }}</td>
                <td>{{ data.nickName }}</td>
                <td>{{ data.category }}</td>
                <td>{{ data.score }}</td>
                <td>{{ data.solveDate }}</td>
              </tr>
            </table>
          </v-tab-item>
        </v-tabs-items>
      </v-card-text>
    </v-card>
  </div>
</template>

<script setup>
import { useRankStore } from "@/stores/rank";
import { ref, onMounted, watch } from "vue";

const rankStore = useRankStore();
const tab = ref('basic')

const searchInfo = ref({
  searchItem: "none",
  searchValue: "",
  orderSt: "score",
  orderByDir: "desc",
  category: tab,
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

watch(
  () => tab.value,
  (nv, ov) => {
    if (nv !== ov) {
      searchInfo.value.category = nv;
      rankStore.searchList(searchInfo.value);
    }
  }
);

const searchList = function () {
  if (searchInfo.value.searchItem === "none") {
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
  rankStore.searchList(searchInfo.value);
});
</script>

<style scoped>
/* RankView.vue */

/* .ranking-board 스타일 수정 */
.ranking-board {
  margin: 70px auto; /* 수직 가운데 정렬 및 위쪽 여백 추가 */
  max-width: 90%; /* 전체 너비의 90% 사용 */
  padding: 30px; /* 내부 여백 추가 */
  border: 1px solid #ddd; /* 테두리 추가 */
  border-radius: 10px; /* 테두리 모서리를 둥글게 */
}

/* 검색 바 스타일 수정 */
.search-bar {
  margin-bottom: 40px; /* 아래쪽 간격 늘리기 */
  text-align: center;
}

.search-bar label {
  margin-right: 15px; /* 레이블과 입력 요소 사이 간격 늘리기 */
}

.search-bar select,
.search-bar input[type="text"],
.search-bar input[type="number"],
.search-bar button {
  margin: 10px 15px; /* 입력 요소 간격 늘리기 */
  padding: 10px 15px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

.search-bar button {
  background-color: #4caf50;
  color: white;
  border: none;
  cursor: pointer;
}

.search-bar button:hover {
  background-color: #45a049;
}

.search-bar button:disabled {
  background-color: #ddd;
  color: #999;
  cursor: not-allowed;
}

/* 랭킹 테이블 스타일 수정 */
.ranking-table {
  margin: 20px auto; /* 수평 가운데 정렬 및 위쪽 여백 추가 */
  width: 100%;
  border-collapse: collapse;
}

.ranking-table th {
  background-color: darkkhaki;
  color: white;
  padding: 12px;
  text-align: center;
}

.ranking-table td {
  padding: 10px;
  text-align: center;
}

.ranking-table tbody tr:nth-child(even) {
  background-color: #f2f2f2;
}

.ranking-table tbody tr:hover {
  background-color: #ddd;
}
</style>