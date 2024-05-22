<template>
  <div class="container">
    <h2>MAKE YOUR ARENA</h2>
    <hr />
    <fieldset>
      <legend>GIVE ME A FIGHT</legend>
      <div class="form-group">
        <label for="field">TITLE : </label>
        <input type="text" id="title" v-model="arena.field" />
      </div>
      <div class="form-group">
        <label for="starter">개최자 : </label>
        <span>{{ nickName }}</span>
      </div>
      <div class="form-group">
        <label for="content">내용 : </label>
        <textarea id="content" cols="30" rows="10" v-model="arena.content"></textarea>
      </div>
      <div class="form-group">
        <button @click="createArena">등록</button>
      </div>
    </fieldset>
  </div>
</template>

<script setup>
import { useArenaStore } from "@/stores/arena";
import { ref } from "vue";
import { useRouter } from "vue-router";

const arenaStore = useArenaStore();
const router = useRouter();

const nickName = sessionStorage.getItem("user");

const arena = ref({
  field: "",
  starter: nickName,
  content: "",
});

const createArena = async () => {
  console.log(arena.value.field);
  if (arena.value.field === "") {
    alert("제목을 입력해주세요");
  } else {
    await arenaStore.createArena(arena.value);
    router.push({ name: "arenaList" });
  }
};
</script>

<style scoped>
.container {
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
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

input[type="text"],
textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #0056b3;
}
</style>
