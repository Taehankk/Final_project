<template>
  <div :class="['container', selectedNation]">
    <div class="nav-links">
      <router-link to="/" class="router-link-home">HOME</router-link>
      <router-link v-if="loginUser === 'admin'" to="/admin" class="router-link">ADMIN</router-link>
    </div>

    <div class="nation">
      <img src="@/assets/nation/korea.jpg" alt="한국" class="nation-img" @click="getFlagImage('korea')">
      <img src="@/assets/nation/japan.jpg" alt="일본" class="nation-img" @click="getFlagImage('japan')">
      <img src="@/assets/nation/china.jpg" alt="중국" class="nation-img" @click="getFlagImage('china')">
      <img src="@/assets/nation/usa.jpg" alt="미국" class="nation-img" @click="getFlagImage('usa')">
      <img src="@/assets/nation/uk.jpg" alt="영국" class="nation-img" @click="getFlagImage('uk')">
      <img src="@/assets/nation/northkorea.jpg" alt="북한" class="nation-img" @click="openNISImage">
    </div>

    <div class="user-controls">
      <img :src="name" alt="사진오류" class="selected-nation-img">
      <router-link v-if="!loginUser" to="/login" class="router-link">LOGIN</router-link>
      <router-link v-if="!loginUser" to="/regist" class="router-link">SIGNUP</router-link>
      <div v-if="loginUser" class="welcome-container">
        <p class="welcome-message">
          {{ loginUser === 'admin' ? '관리자님' : loginUser }} 환영합니다
        </p>
      </div>
      <button v-if="loginUser" @click="logout" class="button">LOGOUT</button>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useUserStore } from "@/stores/user";
import { computed } from "vue";

const userStore = useUserStore();
const loginUser = computed(() => userStore.user);
const logout = () => userStore.logout();

const selectedNation = ref('korea'); // 초기값을 'korea'로 설정

onMounted(() => {
  getFlagImage('korea'); // 페이지 로드 시 한국 국기 표시
});

const images = import.meta.glob('@/assets/nation/*.jpg');

const openNISImage = () => {
  window.open('src/assets/nation/warning.jpg');
  setTimeout(() => {
    window.close();
  }, 2000);
};

const name = ref('');

const getFlagImage = async (nation) => {
  selectedNation.value = nation;
  const flagImages = {
    korea: images['/src/assets/nation/korea.jpg'],
    japan: images['/src/assets/nation/japan.jpg'],
    china: images['/src/assets/nation/china.jpg'],
    usa: images['/src/assets/nation/usa.jpg'],
    uk: images['/src/assets/nation/uk.jpg'],
    northkorea: images['/src/assets/nation/northkorea.jpg']
  };

  name.value = (await flagImages[nation]()).default;
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

/* 네비게이션 링크 스타일 */
.nav-links {
  display: flex;
  align-items: center;
}

.router-link,
.router-link-home,
.button {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-left: 10px;
  text-decoration: none;
}

.router-link-home {
  margin: 0 10px;
}

/* HOME 버튼 스타일 */
.router-link-home,
.button {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-left: 10px;
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

.selected-nation-img {
  width: 40px;
  height: auto;
  margin-right: 10px;
}

.welcome-container {
  display: flex;
  align-items: center;
}

.welcome-message {
  margin-right: 10px;
  text-align: right;
}

/* 각 국가별 배경 색상 */
.container.korea {
  background-color: whitesmoke;
  color: black; /* 글자 색상 */
}

.container.japan {
  background-color: pink;
  color: rgb(233, 81, 164); /* 글자 색상 */
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

/* 각 국가별 버튼 색상 */
.container.korea .router-link,
.container.korea .router-link-home,
.container.korea .button {
  background-color: #5895d6; /* 버튼 배경색 */
  color: white; /* 버튼 글자 색상 */
}

.container.japan .router-link,
.container.japan .router-link-home,
.container.japan .button {
  background-color: #ffffff; /* 버튼 배경색 */
  color: rgb(233, 81, 164); /* 버튼 글자 색상 */
}

.container.china .router-link,
.container.china .router-link-home,
.container.china .button {
  background-color: #ff4d4d; /* 버튼 배경색 */
  color: white; /* 버튼 글자 색상 */
}

.container.northkorea .router-link,
.container.northkorea .router-link-home,
.container.northkorea .button {
  background-color: #ff6600; /* 버튼 배경색 */
  color: white; /* 버튼 글자 색상 */
}

.container.usa .router-link,
.container.usa .router-link-home,
.container.usa .button {
  background-color: #4CAF50; /* 버튼 배경색 */
  color: white; /* 버튼 글자 색상 */
}

.container.uk .router-link,
.container.uk .router-link-home,
.container.uk .button {
  background-color: #333333; /* 버튼 배경색 */
  color: white; /* 버튼 글자 색상 */
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
  .router-link-home,
  .button {
    margin: 5px 0;
  }

  .nation {
    margin-bottom: 10px;
  }
}
</style>
