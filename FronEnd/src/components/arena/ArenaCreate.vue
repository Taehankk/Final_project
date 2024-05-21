<template>
  <div>
    <h2>MAKE YOUR ARENA</h2>
    <hr />
    <fieldset>
      <legend>GIVE ME A FIGHT</legend>
      <div>
        <label for="field">FIELD : </label>
        <input type="text" id="title" v-model="arena.field" />
      </div>
      <div>
        <label for="starter">개최자 : </label>
        {{ nickName }}
      </div>
      <div>
        <label for="content">내용 : </label>
        <textarea
          id="content"
          cols="30"
          rows="10"
          v-model="arena.content"
        ></textarea>
      </div>
      <div>
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
  await arenaStore.createArena(arena.value);
  router.push({ name: "arenaList" });
};
</script>

<style scoped>
input,
textarea {
  border: 1px solid black;
}
</style>
