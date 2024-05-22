<template>
  <main :class="selectedNation">
    <div class="container">
      <h1>다함께 SHOW SHOW SHOW!</h1>
      <h3>당신은 풀수 있다 퀴즈를...</h3>
      | <router-link to="/rank" class="nav-link">RANK</router-link> |
      <router-link to="/arena" class="nav-link">ARENA</router-link> |
    </div>

    <div class="thumbnails-container">
      <router-link to="/game" class="thumbnail-link">
        <div class="thumbnail">
          <img src="@/assets/tmp/tmp1.jpg" alt="Thumbnail 1">
          <h2 class="start-heading">START</h2>
        </div>
      </router-link>
    </div>
  </main>
</template>

<script setup>
import { ref } from 'vue';

const selectedNation = ref(''); // This should be updated based on the nation selected in TheHeaderNav.vue
</script>

<style scoped>
.container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px;
  background-color: #272727;
  color: #fff;
}

/* 각 국가별 스타일 */
main.korea {
  background-color: #c60c30;
}

main.japan {
  background-color: #ffffff;
}

main.china {
  background-color: #de2910;
}

main.northkorea {
  background-color: #024fa2;
}

main.usa {
  background-color: #3c3b6e;
}

main.uk {
  background-color: #00247d;
}

.thumbnails-container {
  margin-top: 20px;
  text-align: center;
}

h1 {
  font-size: 24px;
  margin: 0;
}

h2 {
  font-size: 20px;
  margin: 10px 0 0;
}

.nav-link {
  text-decoration: none;
  color: #fff;
  margin-right: 10px;
  transition: color 0.3s ease;
}

.nav-link:hover {
  color: #007bff;
}

.thumbnail {
  display: inline-block;
  background-color: aquamarine;
  margin-right: 10px;
  cursor: pointer;
}

.thumbnail img {
  width: 200px;
  height: auto;
}

.start-heading {
  margin-top: 5px;
}

.thumbnail-link {
  text-decoration: none;
}

.thumbnail:hover {
  background-color: whitesmoke;
}
</style>
