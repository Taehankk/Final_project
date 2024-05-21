<template>
  <div class="container">
    <router-link to="/" class="router-link">HOME</router-link>

    <router-link v-if="loginUser === 'admin'" to="/admin" class="router-link">ADMIN</router-link>

    <span v-if="loginUser === 'admin'"> | </span>

    <router-link v-if="!loginUser" to="/login" class="router-link">LOGIN</router-link>

    <button v-if="loginUser" @click="logout" class="button">LOGOUT</button>

    <router-link v-if="!loginUser" to="/regist" class="router-link">SIGNUP</router-link>

    <p v-if="loginUser === 'admin'">관리자님 환영합니다.</p>
    <p v-if="loginUser !== null && loginUser !== 'admin'">
      {{ loginUser }} 님 환영합니다
    </p>
    
  </div>
</template>


<script setup>
import { useUserStore } from "@/stores/user";
import { computed } from "vue";

const userStore = useUserStore();

const loginUser = computed(() => userStore.user);

const logout = function () {
  userStore.logout();
};
</script>

<style scoped>
/* 전체 컨테이너 스타일 */
.container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 20px;
  background-color: #f0f0f0;
}

/* 링크 스타일 */
.router-link {
  text-decoration: none;
  color: #333;
  margin-right: 10px;
}

/* 버튼 스타일 */
.button {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
}

/* 버튼 호버 스타일 */
.button:hover {
  background-color: #0056b3;
}

/* 반응형 스타일 */
@media (max-width: 768px) {
  .container {
    flex-direction: column;
    padding: 20px;
  }
  .router-link {
    margin-bottom: 10px;
  }
}

</style>