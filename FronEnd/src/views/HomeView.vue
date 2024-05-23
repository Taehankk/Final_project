<template>
  <v-app>
    <v-main>
      <div class="TheHeaderNav">
        <h1>다함께 SHOW SHOW SHOW!</h1>
        <nav>
          <router-link to="/rank" class="nav-link">RANK</router-link> |
          <router-link to="/arena" class="nav-link">ARENA</router-link>
        </nav>
      </div>
      <v-container>
        <v-row class="thumbnails-row">
          <v-col v-for="(thumbnail, index) in thumbnails" :key="index" cols="3">
            <button class="thumbnail-link" @click="startQuiz(index)">
              <v-card class="thumbnail" outlined hover max-width="280">
                <v-img
                  :width="280"
                  :src="thumbnail.image"
                  :alt="thumbnail.alt"
                ></v-img>
                <h2 class="title-heading">{{ thumbnail.title }}</h2>
              </v-card>
            </button>
          </v-col>
        </v-row>
      </v-container>

      <div class="modal" v-if="openModal">
        <div class="modal-content">
          <span class="close" @click="closeModal">&times;</span>
          <h2 class="modal-title">최고의 게임!</h2>
          <div class="info-details">
            <h3>게임 안내</h3>
            <ul>
              <li>START를 누르면 바로 게임이 시작</li>
              <li>띄어쓰기는 없으니 유의해주세요</li>
              <li>총 10개의 문제가 준비되어 있습니다</li>
              <li>아무것도 입력하지 않으면 넘어갈 수 없습니다</li>
              <li>문제 당 10초의 제한이 있으니 시간초과에 유의하세요</li>
              <li>퀴즈 종료 후 데이터를 저장할 수 있습니다</li>
              <li>모든 카테고리에서 최고의 점수를 획득하세요</li>
            </ul>
          </div>
          <hr />
          <div class="modal-footer">
            <h1>당신은 경험하다.</h1>
            <h1>이 게임은 정말 재밌습니다.</h1>
            <h1>당신의 정보는 안전하게 저장됩니다.</h1>
          </div>
          <div class="modal-buttons">
            <button @click="startGame()" class="start-button">
              게임을 하다
            </button>
          </div>
        </div>
      </div>
    </v-main>
  </v-app>
</template>

<script setup>
import { useRouter } from "vue-router";
import { ref } from "vue";

const router = useRouter();

import basicImage from "@/assets/thumbnail/basic.jpg";
import esportsImage from "@/assets/thumbnail/esports.jpg";
import kboImage from "@/assets/thumbnail/kbo.jpg";
import kleagueImage from "@/assets/thumbnail/kleague.jpg";
import champsImage from "@/assets/thumbnail/champs.jpg";

const thumbnails = [
  { image: basicImage, alt: "Basic", category: "basic", title: "종합문제" },
  {
    image: esportsImage,
    alt: "esports",
    category: "esports",
    title: "Esports",
  },
  { image: kboImage, alt: "KBO", category: "kbo", title: "크보" },
  {
    image: kleagueImage,
    alt: "kleague",
    category: "kleague",
    title: "K-League",
  },
  { image: champsImage, alt: "CHAMPS", category: "champs", title: "해축" },
];

const currentIndex = ref(0);
const openModal = ref(false);

const startQuiz = (index) => {
  currentIndex.value = index;
  openModal.value = true;
};

const startGame = () => {
  closeModal(); // 모달 닫기
  router.push(`/game/${thumbnails[currentIndex.value].category}`);
  console.log(thumbnails[currentIndex.value].category);
};


const closeModal = () => {
  openModal.value = false;
};
</script>

<style scoped>
.v-main{
  background-color: rgb(219, 248, 241);
}

/* 상단 네비게이션 바 스타일 */
.TheHeaderNav {
  margin: auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  background-color: #25adcfa1;
  color: white;
  width: 100%;
}

.TheHeaderNav h1,
.TheHeaderNav h3 {
  margin: 0;
}

.nav-link {
  text-decoration: none;
  color: white;
  margin-right: 10px;
}

/* 상단 네비게이션 바 스타일 */
.TheHeaderNav {
  margin: auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  background-color: #25adcfa1;
  color: white;
  width: 100%;
}

.TheHeaderNav h1 {
  margin: 0;
  font-size: 24px;
  font-weight: bold;
}

.nav-link {
  text-decoration: none;
  color: white;
  margin-right: 10px;
  font-size: 16px;
  transition: color 0.3s ease;
}

.nav-link:hover {
  color: #f0f0f0; /* 호버 시 색상 변경 */
}

/* 썸네일 링크 스타일 */
.thumbnail-link {
  border: none;
  background-color: transparent;
  cursor: pointer;
}

.thumbnail-link:focus {
  outline: none;
}

.thumbnail-link:hover .thumbnail {
  background-color: #f0f0f0;
}

.thumbnail-link:active .thumbnail {
  background-color: #e0e0e0;
}

/* 썸네일 카드 스타일 */
.thumbnail {
  transition: background-color 0.3s ease;
  width: 100%; /* 썸네일이 컬럼에 꽉 차도록 설정 */
  margin-bottom: 10px; /* 각 썸네일 사이에 10px의 마진 추가 */
}

/* 모달 스타일 */
.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  z-index: 1000;
  max-width: 90%; /* 모달의 최대 너비 설정 */
}

.close {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 1.5rem;
  cursor: pointer;
  color: #aaa;
  transition: color 0.3s ease;
}

.close:hover {
  color: #666;
}

.modal-content {
  margin-top: 20px; /* 내용과 모달 상단 간격 설정 */
}

.modal-title {
  margin-bottom: 20px;
  font-size: 1.5rem;
}

.info-details {
  margin-bottom: 20px;
}

.info-details p {
  font-size: 1.2rem;
  margin-bottom: 10px;
}

.modal-buttons {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px; /* 버튼과 내용 간격 설정 */
}

.start-button {
  background-color: #4caf50;
  color: white;
  padding: 10px 20px;
  margin: 0 10px;
  font-size: 1rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: opacity 0.3s ease;
}

.start-button:hover {
  opacity: 0.8;
}

/* Responsiveness */
@media (max-width: 600px) {
  .modal {
    width: 90%; /* 모달이 화면 크기에 따라 줄어들도록 설정 */
  }
}
</style>
