<template>
  <div>
    <h2>ARENA LIST</h2>
    <hr />
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
    <router-link :to="`/arena/create`">Arena 등록</router-link>
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
/* 스타일링을 필요에 따라 추가하세요 */
table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ccc;
  padding: 8px;
  text-align: center;
}

th {
  background-color: #f2f2f2;
}
</style>
