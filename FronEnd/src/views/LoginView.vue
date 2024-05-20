<template>
  <div>
    <fieldset>
      <div>
        <label for="id">ID</label>
        <input type="text" id="id" v-model="loginData.userId" @keyup.enter="login"/>
      </div>
      <div>
        <label for="password">PW</label>
        <input type="password" id="password" v-model="loginData.password" @keyup.enter="login"/>
        
      </div>
      <div>
        <button @click="login">Login</button>
      </div>
    </fieldset>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { ref } from "vue";

const userStore = useUserStore();

const loginData = ref({
  userId: "",
  password: "",
});

const login = function () {
  userStore.loginUser(loginData.value);
};
</script>

<style scoped></style>
