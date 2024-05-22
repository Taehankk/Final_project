<template>
  <div class="container">
    <div class="title">
      <h1>A R E N A</h1>
      <br>
      <h2>여러분의 생각을 마음껏 뽐내다.</h2>
      <br>
      <h3>이곳은 투기장 입니다.</h3>
    </div>
    <hr />
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
  /* 배경 이미지 경로 설정 */
  background-image: url("@/assets/tmp/sentence.jpg");
  /* 배경 이미지를 화면에 꽉 채우도록 설정 */
  background-size: cover;
  /* 배경 이미지를 중앙에 정렬 */
  background-position: center;
  /* 전체 페이지 높이를 최소화하여 내용이 화면에 맞춰 보이도록 설정 */
  min-height: 100vh;
  /* 내용을 중앙에 정렬 */
  display: flex;
  flex-direction: column;
  justify-content: flex-start; /* 위쪽 정렬로 변경 */
  align-items: center;
}

/* 헤딩 스타일 */
.title {
  margin-top: 20px; /* 제목 위쪽 여백 추가 */
  margin-bottom: 40px; /* 제목 아래쪽 여백 추가 */
  text-align: center; /* 가운데 정렬 */
  color: whitesmoke; /* 텍스트 색상을 밝은색으로 설정 */
  background: rgba(0, 0, 0, 0.3);
}

.title h1, h3 {
  font-size: 40px;
}

.title h2 {
  font-size: 20px;
}

/* 목록 테이블 스타일 */
table {
  width: 100%; /* 테이블의 너비를 100%로 설정 */
  margin: 20px 0; /* 위쪽과 아래쪽 여백 추가 */
  border-collapse: collapse;
  background-color: rgba(
    255,
    255,
    255,
    0.8
  ); /* 테이블의 배경색을 투명도가 있는 백색으로 설정 */
  border-radius: 8px; /* 테이블에 모서리를 둥글게 설정 */
  font-size: 16px; /* 글꼴 크기를 늘림 */
}

th,
td {
  border: 1px solid #eee;
  padding: 16px; /* 셀 내부 여백을 늘림 */
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
  padding: 12px 24px; /* 버튼 내부 여백을 조정 */
  background-color: #28a745;
  color: white;
  text-decoration: none;
  border: none;
  border-radius: 4px;
  transition: background-color 0.3s ease;
}

.create-btn:hover {
  background-color: #218838;
}
</style>
