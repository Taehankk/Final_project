<template>
  <div class="arena-update-container">
    <h2>ARENA UPDATE</h2>
    <hr />
    <fieldset>
      <legend>GIVE ME A FIGHT</legend>
      <div class="form-group">
        <label for="field">FIELD :</label>
        <input type="text" id="field" v-model="arena.field" />
      </div>
      <div class="form-group">
        <label for="starter">개최자 :</label>
        <span>{{ arena.starter }}</span>
      </div>
      <div class="form-group">
        <label for="content">내용 :</label>
        <textarea id="content" rows="5" v-model="arena.content"></textarea>
      </div>
      <div class="form-group">
        <button @click="updateArena">수정 완료</button>
      </div>
    </fieldset>
  </div>
</template>

<script setup>
import { useArenaStore } from "@/stores/arena";
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";

const arenaStore = useArenaStore();
const route = useRoute();
const router = useRouter();

const arena = ref({});

const updateArena = async () => {
  await arenaStore.updateArena(arena.value);
  router.push({ name: "arenaList" });
};

onMounted(async () => {
  await arenaStore.getArenaNow(route.query.arenaId);
  arena.value = arenaStore.arena;
});
</script>

<style scoped>
.arena-update-container {
  padding: 20px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: inline-block;
  width: 100px;
}

input[type="text"],
textarea {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  padding: 8px 16px;
  font-size: 16px;
  border: 1px solid aquamarine;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
