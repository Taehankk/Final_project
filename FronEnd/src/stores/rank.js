import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_RANK_API = `http://localhost:8080/ansmoon/problem`;
export const useRankStore = defineStore("rank", () => {
  const dataList = ref([]);

  const searchList = async (data) => {
    console.log(data);
    await axios
      .get(`${REST_RANK_API}/search`, {
        params: data,
      })
      .then((response) => {
        dataList.value = response.data;
      });
  };
  return { dataList, searchList };
});