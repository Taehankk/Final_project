<template>
  <div class="container">
    <div class="title">
      <h1>A R E N A</h1>
      <h2>여러분의 생각을 마음껏 뽐내다.</h2>
      <h3>이곳은 투기장 입니다.</h3>
    </div>
    <div class="table-container">
      <router-link :to="`/arena/create`" class="create-btn">Arena 등록</router-link>
      <table>
        <thead>
          <tr>
            <th>번호</th>
            <th>경기장</th>
            <th>주최자</th>
            <th>관심</th>
            <th>개최일</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(arena, index) in arenaStore.arenaList" :key="arena.id">
            <td>{{ index + 1 }}</td>
            <td>
              <router-link :to="`/arena/detail/${arena.arenaId}`">{{ arena.field }}</router-link>
            </td>
            <td>{{ arena.starter }}</td>
            <td>{{ arena.interest }}</td>
            <td>{{ arena.openDay }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <hr />
  </div>
</template>

<script setup>
import { useArenaStore } from "@/stores/arena";
import { onMounted } from "vue";

const arenaStore = useArenaStore();

onMounted(() => {
  arenaStore.getArenaList();
});
</script>

<style scoped>
/* 전체 페이지 컨테이너 스타일 */
.container {
  background-image: url("@/assets/tmp/sentence.jpg");
  background-size: cover;
  background-position: center;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

/* 테이블 컨테이너 스타일 */
.table-container {
  width: 100%;
  max-width: 1200px;
  margin-bottom: 20px;
  flex-shrink: 0; /* 테이블 컨테이너가 축소되지 않도록 설정 */
}

/* 헤딩 스타일 */
.title {
  margin-top: 10px; /* 제목 위쪽 여백 감소 */
  margin-bottom: 20px; /* 제목 아래쪽 여백 감소 */
  text-align: center;
  color: whitesmoke;
  background: rgba(0, 0, 0, 0.3);
  padding: 10px;
  border-radius: 8px;
}

.title h1, h3 {
  font-size: 50px;
}

.title h2 {
  font-size: 30px;
}

/* 목록 테이블 스타일 */
table {
  width: 100%;
  margin: 20px 0;
  border-collapse: collapse;
  background-color: rgba(255, 255, 255, 0.9);
  border-radius: 8px;
  font-size: 18px;
}

th,
td {
  border: 1px solid #eee;
  padding: 20px;
  text-align: center;
}

th {
  background-color: #f5f5f5;
  color: #333;
}

td a {
  color: #007bff;
  text-decoration: none;
  transition: color 0.3s ease;
}

td a:hover {
  color: #0056b3;
}

/* 추가 버튼 스타일 */
.create-btn {
  display: inline-block;
  padding: 15px 30px;
  background-color: #28a745;
  color: white;
  text-decoration: none;
  border: none;
  border-radius: 8px;
  transition: background-color 0.3s ease;
  font-size: 18px;
  margin-bottom: 20px;
}

.create-btn:hover {
  background-color: #218838;
}
</style>
