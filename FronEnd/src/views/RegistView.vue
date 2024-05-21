<template>
  <div class="regist-container">
    <div class="regist-form">
      <fieldset>
        <legend>Registration</legend>
        <div class="form-group">
          <label for="name">NAME</label>
          <input type="text" id="name" v-model="registData.userName" />
        </div>
        <div class="form-group">
          <label for="id">ID</label>
          <input type="text" id="id" v-model="registData.userId" />
          <button @click="checkId">CHECK</button>
        </div>
        <div class="form-group">
          <label for="password">PW</label>
          <input
            type="password"
            id="password"
            v-model="registData.password"
            maxlength="20"
          />
          <p v-show="!boolPass">
            8-20 characters, only !@#$%^&* special characters are allowed
          </p>
        </div>
        <div class="form-group">
          <label for="nickname">NICKNAME</label>
          <input type="text" id="nickname" v-model="registData.nickName" />
          <button @click="checkNick">CHECK</button>
        </div>
        <div class="form-group">
          <button @click="registUser">REGISTER</button>
        </div>
      </fieldset>
    </div>
  </div>
</template>

<script setup>
import { computed, onMounted, ref, watch } from "vue";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";

const router = useRouter();
const userStore = useUserStore();
const idconf = ref(false);
const nickconf = ref(false);
const registData = ref({
  userName: "",
  userId: "",
  password: "",
  nickName: "",
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
  if (registData.value.userId === "") {
    alert("입력하다 당신의 아이디를");
  } else {
    await userStore.checkParam("userId", registData.value.userId);
    idconf.value = userStore.duplcheck;
  }
};

watch(
  () => registData.value.password,
  (newPass) => {
    if (newPass.length > 20) {
    }
    userStore.checkPass(newPass);
  }
);

const checkNick = async () => {
  if (registData.value.nickName === "") {
    alert("Enter your nickname");
  } else {
    await userStore.checkParam("nickname", registData.value.nickName);
    nickconf.value = userStore.duplcheck;
  }
};

onMounted(() => {
  userStore.initPass();
});

const redirectToSignup = function () {
  router.push("/regist"); // Redirect to the registration page
};
</script>

<style scoped>
.regist-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.regist-form {
  width: 100%; /* Make the form width 100% */
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
  margin-bottom: 20px; /* Increased margin */
  display: flex;
  align-items: center;
}

label {
  width: 100px; /* Fixed width for labels */
}

input[type="text"],
input[type="password"] {
  flex: 1;
  padding: 8px;
  border: 1px solid #ccc; /* Add border */
  border-radius: 5px;
}

button {
  width: 80px; /* Fixed width for buttons */
  padding: 8px;
  font-size: 16px;
  border: 1px solid aquamarine;
  border-radius: 5px;
  cursor: pointer;
  background: darkturquoise;
}

.signup-link {
  margin-left: 10px; /* Add margin between button and signup link */
}

button:hover {
  background-color: #0056b3;
}
</style>
