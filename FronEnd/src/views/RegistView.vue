<template>
  <v-container class="regist-container" fluid>
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="6">
        <v-card>
          <v-card-title>
            <h2>Registration</h2>
          </v-card-title>
          <v-card-text>
            <v-form>
              <v-text-field
                v-model="registData.userName"
                label="NAME"
                outlined
                required
              ></v-text-field>
              <v-text-field
                v-model="registData.userId"
                label="ID"
                outlined
                required
              >
                <template v-slot:append>
                  <v-btn @click="checkId" outlined>CHECK</v-btn>
                </template>
              </v-text-field>
              <v-text-field
                v-model="registData.password"
                label="PW"
                type="password"
                outlined
                maxlength="20"
                required
              ></v-text-field>
              <v-alert
                v-if="!boolPass"
                type="error"
                border="left"
                elevation="2"
                outlined
              >
                8-20 characters, only !@#$%^&* special characters are allowed
              </v-alert>
              <v-text-field
                v-model="registData.nickName"
                label="NICKNAME"
                outlined
                required
              >
                <template v-slot:append>
                  <v-btn @click="checkNick" outlined>CHECK</v-btn>
                </template>
              </v-text-field>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" @click="registUser">REGISTER</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { computed, ref, watch, onMounted } from 'vue';
import { useUserStore } from '@/stores/user';
import { useRouter } from 'vue-router';

const router = useRouter();
const userStore = useUserStore();
const idconf = ref(false);
const nickconf = ref(false);
const registData = ref({
  userName: '',
  userId: '',
  password: '',
  nickName: ''
});

const boolPass = computed(() => userStore.boolPass);

const registUser = function () {
  if (registData.value.userName === "") {
    alert("이름을 입력하다.");
  } else if (registData.value.userId === "") {
    alert("E̶̢̡͔͎̦̰̝̯̭̪͋͊̉͌͌́̒̉̍̕N̠͈̖̦̺͗̅͋̍̏͊͘͝T̵̨͙̖̹͓̈̔́́͂͝͡E̡̢̻̺̥͔̿̉̿̆̄̃͗̚͢͟R̯̲̺͉̎͗̓̈͌̈͒̒̃͜͡ Ç͔͓̬̊̅̿͂̔̚͝ͅO̴̥͇̠͔̳̱̳̺̲͊̐̎̑͞R̻̱̟͓̀̆̏͌̾́̏͌͋͟͡Ṙ̹̰̟̘̦̪̠̗̲͐͆͗͋͘͢E̴͉̜̖̥̬̤̪͊̉̃́̔̌̂̅̍̚C̨̥̝̘̠̉̃̐̈͌̅̏͜Ṭ̢̛̗̮͓̯̦̻͆̄̈͗̉ Y͎̟̩̟͖̓͑̀̀̈́͋̍O͈̬̤͈̅͒͆͊̉̿̅͛̚͜Ụ̷̢̝̻͇̥͕̭̟͛̀͊̀́̓̅͑̄͛ͅŔ̴͉̙̦̜̥̤̼̋̇̃̽̔̃͞͠ İ̷̛̜̟̫̹̲͆̑̑̈́͞D̡̛͙̬̠̰̫̱͓̤̀̃̆͐̅̕͡͡");
  } else if (registData.value.password === "") {
    alert("비밀번호를 제발 좀 똑바로 입력하세요");
  } else if (!boolPass.value) {
    console.log("Password write error");
    alert("비밀번호를 제발 좀 똑바로 입력하세요");
  } else if (registData.value.nickName === "") {
    alert("당신의 nickname 만들어야 한다.");
  } else {
    if (idconf.value && nickconf.value) {
      userStore.registUser(registData.value);
    } else if (!idconf.value) {
      alert("Check ID duplication");
    } else if (!nickconf.value) {
      alert("Check NICKNAME duplication");
    }
  }
};

const checkId = async () => {
  if (registData.value.userId === '') {
    alert('왜 아이디 안 적지?');
  } else {
    await userStore.checkParam('userId', registData.value.userId);
    idconf.value = userStore.duplcheck;
  }
};

watch(
  () => registData.value.password,
  (newPass) => {
    userStore.checkPass(newPass);
  }
);

const checkNick = async () => {
  if (registData.value.nickName === '') {
    alert('NickName 필요');
  } else {
    await userStore.checkParam('nickname', registData.value.nickName);
    nickconf.value = userStore.duplcheck;
  }
};

onMounted(() => {
  userStore.initPass();
});
</script>

<style scoped>
.regist-container {
  height: 70vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
