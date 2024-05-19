import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_RANK_API = `http://localhost:8080/ansmoon/problem`;
export const useRankStore = defineStore("rank", () => {

    const dataList = ref([]);
    const getDataList = function(order) {
        axios.get(`${REST_RANK_API}/rank`, {
            params: order
        })
        .then((response) => {
            dataList.value = response.data;
        })
    }

  return { dataList, getDataList };
});
