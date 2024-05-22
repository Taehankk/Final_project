<template>
  <div class="arena-detail-container">
    <div class="title-bar">
      <h2>ARENA DETAIL</h2>
    </div>
    <div class="content-bar">
      <div class="arena-detail">
        <div class="title"><strong>TITLE :</strong> {{ arenaStore.arena.field }}</div>
        <div class="boss"><strong>주최자 :</strong> {{ arenaStore.arena.starter }}</div>
        <div class="regDate"><strong>개최일 :</strong> {{ arenaStore.arena.openDay }}</div>
        <div class="interest"><strong>관심 :</strong> {{ arenaStore.arena.interest }}</div>
        <div class="content"><strong>내용 :</strong> {{ arenaStore.arena.content }}</div>
      </div>

      <div class="action-buttons" v-if="userCheck">
        <button @click="deleteArena">삭제</button>
        <button @click="updateArena">수정</button>
      </div>
    </div>

    <div class="fight-section">
      <FightList />
    </div>
  </div>
</template>

<script setup>
import { useArenaStore } from "@/stores/arena";
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import FightList from "../fight/FightList.vue";

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
/* Title Bar */
.title-bar {
  background-color: #414141;
  padding: 15px;
}

.title-bar h2 {
  color: #fff;
  margin: 0;
}

/* Content Bar */
.content-bar {
  border: 1px solid #e1e1e1;
  border-top: none;
}

.arena-detail {
  padding: 20px;
}

.arena-detail > div {
  margin-bottom: 10px;
}

.arena-detail strong {
  margin-right: 10px;
}

/* Action Buttons */
.action-buttons {
  padding: 20px;
  border-top: 1px solid #e1e1e1;
}

.action-buttons button {
  margin-right: 10px;
  padding: 8px 15px;
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;
}

.action-buttons button:last-child {
  margin-right: 0;
}

/* Fight Section */
.fight-section {
  margin-top: 20px;
}

/* Fight List Style */
/* You can add styles for the FightList component here */
</style>
