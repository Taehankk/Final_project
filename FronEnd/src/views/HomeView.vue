<template>
  <main>
    <div>
      <h1>homeview</h1>
      <v-window v-model="activeCard">
        <v-window-item v-for="n in length" :key="n">
          <v-card>
            <v-card-title>카드 {{ n }} 제목</v-card-title>
            <v-card-text>
              <input
                type="text"
                v-model="cards[n - 1]"
                :placeholder="'입력하세요 ' + n"
                @keyup.enter="printInput(n)"
              />
              <v-btn @click="printInput(n)">출력</v-btn>
            </v-card-text>
          </v-card>
        </v-window-item>
      </v-window>
    </div>
  </main>
</template>

<script>
import { ref } from "vue";
export default {
  data() {
    return {
      length: 10,
      activeCard: 0,
      cards: ref([]),
    };
  },
  methods: {
    printInput(index) {
      console.log(this.cards.length);
      if (this.activeCard < this.length - 1) {
        this.activeCard++;
        console.log("카드 " + index + "의 입력 값:", this.cards[index - 1]);
      } else {
        console.log("카드 " + index + "의 입력 값:", this.cards[index - 1]);
      }
    },
  },
};
</script>