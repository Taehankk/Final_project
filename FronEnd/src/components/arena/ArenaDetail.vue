<template>
  <div class="arena-detail-container">
    <h2>ARENA DETAIL</h2>
    <hr />
    <div class="arena-detail">
      <div><strong>경기장:</strong> {{ arenaStore.arena.field }}</div>
      <div><strong>주최자:</strong> {{ arenaStore.arena.starter }}</div>
      <div><strong>개최일:</strong> {{ arenaStore.arena.openDay }}</div>
      <div><strong>관심:</strong> {{ arenaStore.arena.interest }}</div>
      <div><strong>내용:</strong> {{ arenaStore.arena.content }}</div>
    </div>

    <div class="action-buttons" v-if="userCheck">
      <button @click="deleteArena">삭제</button>
      <button @click="updateArena">수정</button>
    </div>

    <div class="fight-section">
      <h4>FIGHT</h4>
      <!-- 여기에 경기 정보 표시 -->
    </div>
  </div>
</template>

<script setup>
import { useArenaStore } from "@/stores/arena";
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";

const arenaStore = useArenaStore();
const route = useRoute();
const router = useRouter();
const loginUser = sessionStorage.getItem("user");
const userCheck = ref(true);

const deleteArena = async () => {
  await arenaStore.deleteArena(route.params.arenaId);
  router.push({ name: "arenaList" });
};

const updateArena = function () {
  router.push({
    name: "updateArena",
    query: {
      arenaId: route.params.arenaId,
    },
  });
};

onMounted(async () => {
  if (!sessionStorage.getItem("visitFlag" + route.params.arenaId)) {
    sessionStorage.setItem("visitFlag" + route.params.arenaId, true);
    await arenaStore.updateInterest(route.params.arenaId, 1);
  }
  await arenaStore.getArenaNow(route.params.arenaId);
  if (loginUser !== arenaStore.arena.starter) {
    userCheck.value = false;
  } else {
    userCheck.value = true;
  }
});
</script>

<style scoped>
.arena-detail-container {
  padding: 20px;
}

.arena-detail {
  margin-bottom: 20px;
}

.action-buttons button {
  margin-right: 10px;
  padding: 8px 16px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
  cursor: pointer;
}

.fight-section {
  border-top: 1px solid #ccc;
  padding-top: 20px;
}

h4 {
  margin-top: 0;
}
</style>
