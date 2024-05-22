<template>
   <div :class="['container', selectedNation]">
    <div class="nav-links">
      <router-link to="/" class="router-link">HOME</router-link>
      <router-link v-if="loginUser === 'admin'" to="/admin" class="router-link">ADMIN</router-link>
    </div>

    <div class="nation">
      <img src="@/assets/tmp/korea.jpg" alt="한국" class="nation-img" @click="getFlagImage('korea')">
      <img src="@/assets/tmp/japan.jpg" alt="일본" class="nation-img" @click="getFlagImage('japan')">
      <img src="@/assets/tmp/china.jpg" alt="중국" class="nation-img" @click="getFlagImage('china')">
      <img src="@/assets/tmp/northkorea.jpg" alt="북한" class="nation-img" @click="openNISImage">
      <img src="@/assets/tmp/usa.jpg" alt="미국" class="nation-img" @click="getFlagImage('usa')">
      <img src="@/assets/tmp/uk.jpg" alt="영국" class="nation-img" @click="getFlagImage('uk')">
    </div>

    <div class="user-controls">
      <router-link v-if="!loginUser" to="/login" class="router-link">LOGIN</router-link>
      <router-link v-if="!loginUser" to="/regist" class="router-link">SIGNUP</router-link>
      <div v-if="loginUser" class="welcome-container">
        <img v-if="selectedNation" :src="`@/assets/tmp/${name}.jpg`" alt="Selected Nation" class="selected-nation-img">
        <p class="welcome-message">
          {{ loginUser === 'admin' ? '관리자님' : loginUser }} 환영합니다
        </p>
      </div>
      <button v-if="loginUser" @click="logout" class="button">LOGOUT</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useUserStore } from "@/stores/user";
import { computed } from "vue";

const userStore = useUserStore();
const loginUser = computed(() => userStore.user);
const logout = () => userStore.logout();

const selectedNation = ref('');

const openNISImage = () => {
  window.open('@/assets/tmp/nis.jpg','@/assets/tmp/human.jpg', '_blank');
  setTimeout(() => {
    window.close();
  }, 2000);
};

const name = ref("");

const getFlagImage = (nation) => {
  selectedNation.value = nation;
  const flagImages = {
    korea: '@/assets/tmp/korea.jpg',
    japan: '@/assets/tmp/japan.jpg',
    china: '@/assets/tmp/china.jpg',
    northkorea: '@/assets/tmp/northkorea.jpg',
    usa: '@/assets/tmp/usa.jpg',
    uk: '@/assets/tmp/uk.jpg',
  };
  name.value = nation;
  // console.log(name.value);
};
</script>

<style scoped>
/* 전체 컨테이너 스타일 */
.container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  background-color: seashell;
}

/* 각 국가별 배경 색상 */
.container.korea {
  background-color: whitesmoke;
  color: black; /* 글자 색상 */
}

.container.japan {
  background-color: pink;
  color: black; /* 글자 색상 */
}

.container.china {
  background-color: red;
  color: white; /* 글자 색상 */
}

.container.northkorea {
  background-color: #024fa2;
  color: white; /* 글자 색상 */
}

.container.usa {
  background-color: antiquewhite;
  color: black; /* 글자 색상 */
}

.container.uk {
  background-color: black;
  color: white; /* 글자 색상 */
}

/* 네비게이션 링크 스타일 */
.nav-links {
  display: flex;
  align-items: center;
}

.router-link {
  text-decoration: none;
  color: #333;
  margin: 0 10px;
}

/* 이미지 컨테이너 및 이미지 스타일 */
.nation {
  display: flex;
  justify-content: space-around;
  flex: 1;
}

.nation-img {
  width: 80px;
  height: auto;
  max-width: 100%;
  margin: 0 5px;
  cursor: pointer;
}

/* 사용자 컨트롤 스타일 */
.user-controls {
  display: flex;
  align-items: center;
}

.welcome-container {
  display: flex;
  align-items: center;
}

.selected-nation-img {
  width: 40px;
  height: auto;
  margin-right: 10px;
}

.welcome-message {
  margin-right: 10px;
  text-align: right;
}

/* 버튼 스타일 */
.button {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-left: 10px;
}

/* 각 국가별 버튼 색상 */
.container.korea .button {
  background-color: #007bff; /* 버튼 배경색 */
  color: white; /* 버튼 글자 색상 */
}

.container.japan .button {
  background-color: #007bff;
  color: white;
}

.container.china .button {
  background-color: #007bff;
  color: white;
}

.container.northkorea .button {
  background-color: #007bff;
  color: white;
}

.container.usa .button {
  background-color: #007bff;
  color: white;
}

.container.uk .button {
  background-color: #007bff;
  color: white;
}

/* 반응형 스타일 */
@media (max-width: 768px) {
  .container {
    flex-direction: column;
  }

  .nav-links,
  .user-controls {
    justify-content: center;
    margin-bottom: 10px;
  }

  .router-link,
  .button {
    margin: 5px 0;
  }

  .nation {
    margin-bottom: 10px;
  }
}
</style>
