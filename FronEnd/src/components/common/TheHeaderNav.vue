<template>
    <div>      
      <RouterLink to="/">HOME</RouterLink> |
      <RouterLink to="/admin" v-if="loginUser === 'admin'">ADMIN | </RouterLink>
      <RouterLink to="/login" v-if="!loginUser">LOGIN</RouterLink>
      <button @click="logout" v-if="loginUser">LOGOUT</button> |      
      <RouterLink to="/regist" v-if="!loginUser">SIGNUP</RouterLink>
      <p v-if="loginUser">{{ loginUser }} 님 환영합니다</p>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user'; 
import { computed } from 'vue';

const userStore = useUserStore();

const loginUser = computed(() => userStore.user)

const logout = function() {
  userStore.logout();
}
</script>

<style scoped>

</style>