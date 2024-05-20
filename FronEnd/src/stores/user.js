import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useRoute, useRouter } from "vue-router";

const REST_USER_API = `http://localhost:8080/ansmoon/user`;
export const useUserStore = defineStore("user", () => {
  const router = useRouter();

  const duplcheck = ref(false);
  const user = ref(sessionStorage.getItem("user"));

  const boolPass = ref(true);

  const loginUser = function (loginData) {
    axios
      .post(`${REST_USER_API}/login`, loginData)
      .then((response) => {
        const nickName = response.data;
        sessionStorage.setItem("user", nickName);
        user.value = nickName;
        router.push({ name: "home" });
      })
      .catch(() => {
        alert("로그인 정보를 확인하세요");
      });
  };

  const logout = function () {
    sessionStorage.removeItem("user");
    user.value = null;

    axios.get(`${REST_USER_API}/logout`).then(() => {
      alert("로그아웃 되었습니다.");
      router.push({ name: "home" });
    });
  };

  const checkPass = function (password) {
    let regPass = /^[a-zA-Z0-9!@#$%^*]{8,20}$/;
    if (!regPass.test(password)) {
      boolPass.value = false;
    } else {
      boolPass.value = true;
    }
  };

  const registUser = function (registData) {
    axios.post(`${REST_USER_API}/regist`, registData).then((response) => {
      alert("등록 성공");
      router.push({ name: "login" });
    });
  };

  const checkParam = async (name, value) => {
    await axios
      .get(`${REST_USER_API}/check?name=${name}&value=${value}`)
      .then((response) => {
        duplcheck.value = true;
        alert("사용 가능합니다");
      })
      .catch(() => {
        duplcheck.value = false;
        alert("중복입니다");
      });
  };

  const initPass = function () {
    boolPass.value = true;
  };

  return {
    user,
    loginUser,
    logout,
    boolPass,
    checkPass,
    registUser,
    checkParam,
    duplcheck,
    initPass,
  };
});