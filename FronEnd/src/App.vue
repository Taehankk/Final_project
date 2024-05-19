<template>
  <div>
    <TheHeaderNav/>
    <RouterView/>
  </div>
</template>

<script setup>
import TheHeaderNav from './components/common/TheHeaderNav.vue';
import { RouterView } from 'vue-router';

</script>

<style scoped>

</style>