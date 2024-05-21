<template>
  <div class="login-container">
    <div>
  <h1>Login 창</h1>
  </div>
    <div class="login-form">
      <h3>당신은 로그인을 할 수 있습니다.</h3>
      <fieldset>
        <div class="form-group">
          <label for="id">ID</label>
          <input type="text" id="id" v-model="loginData.userId" @keyup.enter="login"/>
        </div>
        <div class="form-group">
          <label for="password">PW</label>
          <input type="password" id="password" v-model="loginData.password" @keyup.enter="login"/>
        </div>
        <div class="form-group">
          <button @click="login">Login</button>
        </div>
        <div class="form-group">
          <button @click="redirectToSignup">Sign Up</button>
        </div>
      </fieldset>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const userStore = useUserStore();

const loginData = ref({
  userId: "",
  password: "",
});

const login = function () {
  userStore.loginUser(loginData.value);
};

const redirectToSignup = function () {
  router.push("/regist"); // Redirect to the registration page
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin-top: 50px; /* Adjusted margin-top */
}

.login-form {
  max-width: 300px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

fieldset {
  border: none;
}

legend {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 10px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input[type="text"],
input[type="password"],
button {
  width: 100%;
  padding: 8px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
