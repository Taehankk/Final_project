<template>
  <div>
    <fieldset>
      <div>
        <label for="name">NAME</label>
        <input type="text" id="name" v-model="registData.userName" />
      </div>
      <div>
        <label for="id">ID</label>
        <input type="text" id="id" v-model="registData.userId" @keyup.enter="checkId"/>
        <button @click="checkId">CHECK</button>
      </div>
      <div>
        <label for="password">PW</label>
        <input
          type="password"
          id="password"
          v-model="registData.password"
          maxlength="20"
        />
        <p v-show="!boolPass">
          8-20자리의 비밀번호를 입력하세요, 특수문자는 !@#$%^&* 만 사용
          가능합니다
        </p>
      </div>
      <div>
        <label for="nickname">NICKNAME</label>
        <input type="text" id="nickname" v-model="registData.nickName" @keyup.enter="checkNick"/>
        <button @click="checkNick">CHECK</button>
      </div>
      <div>
        <button @click="registUser">REGIST</button>
      </div>
    </fieldset>
  </div>
</template>

<script setup>
import { computed, onMounted, ref, watch } from "vue";
import { useUserStore } from "@/stores/user";

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
    alert("이름을 입력하세요");
  } else if (registData.value.userId === "") {
    alert("ID를 입력하세요");
  } else if (registData.value.password === "") {
    alert("비밀번호를 입력하세요");
  } else if (!boolPass.value) {
    console.log("비밀번호 작성 오류");
    alert("비밀번호를 확인하세요");
  } else if (registData.value.nickName === "") {
    alert("닉네임을 입력하세요");
  } else {
    if (idconf.value && nickconf.value) {
      userStore.registUser(registData.value);
    } else if (!idconf.value) {
      alert("ID 중복을 확인하세요");
    } else if (!nickconf.value) {
      alert("NICKNAME 중복을 확인하세요");
    }
  }
};

const checkId = async () => {
  // console.log("idcheck");
  if (registData.value.userId === "") {
    alert("ID를 입력하세요");
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
  // console.log("nickcheck");
  if (registData.value.nickName === "") {
    alert("닉네임을 입력하세요");
  } else {
    await userStore.checkParam("nickname", registData.value.nickName);
    nickconf.value = userStore.duplcheck;
  }
};

onMounted(() => {
  userStore.initPass();
});
</script>

<style scoped>
input {
  margin-right: 3px;
  border: 1px solid black;
  width: 200px;
}

button {
  margin-right: 3px;
  border: 1px solid black;
}
</style>
