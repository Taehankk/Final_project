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
  if (arena.value.field === "") {
    alert("제목을 입력해주세요");
  } else {
    await arenaStore.updateArena(arena.value);
    router.push({ name: "arenaList" });
  }
};


onMounted(async () => {
  await arenaStore.getArenaNow(route.query.arenaId);
  arena.value = arenaStore.arena;
});
</script>

<style scoped>
.arena-update-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
  text-align: center;
  color: #333;
  font-size: 24px;
  margin-bottom: 20px;
}

hr {
  border: none;
  border-top: 1px solid #ccc;
  margin-bottom: 20px;
}

fieldset {
  border: 1px solid #ccc;
  padding: 20px;
  border-radius: 8px;
  margin-bottom: 20px;
}

legend {
  font-size: 18px;
  font-weight: bold;
  color: #333;
  margin-bottom: 10px;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: inline-block;
  width: 100px;
  font-weight: bold;
}

input[type="text"],
textarea {
  width: calc(100% - 100px);
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  padding: 8px 16px;
  font-size: 16px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #0056b3;
}

</style>
