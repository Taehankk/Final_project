import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_ADMIN_API = `http://localhost:8080/ansmoon/admin`;
export const useAdminStore = defineStore("admin", () => {

  const userList = ref([])
  const getUserList = function () {
    axios.get(`${REST_ADMIN_API}/list`)
    .then((response) => {
      userList.value = response.data;
    })
  }

  const deleteUser = function (userId) {
    console.log(userId);
    axios.delete(`${REST_ADMIN_API}/delete/${userId}`)
    .then(() => {
      alert(userId + "유저가 삭제되었습니다.");
      getUserList();
    })
  }

  return { userList, getUserList, deleteUser };
});
