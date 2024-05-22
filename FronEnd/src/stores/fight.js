import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_FIGHT_API = `http://localhost:8080/ansmoon/fight`;
export const useFightStore = defineStore("fight", () => {
  const fightList = ref([]);
  const fightData = ref({});
  const createData = ref({
    arenaId: "",
    content: "",
    fighter: sessionStorage.getItem("user"),
  });

  const getFightList = async (id) => {
    await axios.get(`${REST_FIGHT_API}/list?id=${id}`).then((response) => {
      fightList.value = response.data;
    });
  };

  const getFightOne = async (fightId) => {
    await axios.get(`${REST_FIGHT_API}/list/${fightId}`).then((response) => {
      fightData.value = response.data;
    });
  };

  const updateFight = async () => {
    await axios.put(`${REST_FIGHT_API}/update`, fightData.value).then(() => {
      alert("수정 완료");
    });
  };

  const createFight = async () => {
    console.log(createData.value);
    await axios.post(`${REST_FIGHT_API}/create`, createData.value).then(() => {
      alert("등록 완료");
    });
  };

  const deleteFight = async (id) => {
    await axios.delete(`${REST_FIGHT_API}/delete?id=${id}`).then(() => {
      alert("삭제 완료");
    });
  };

  return {
    fightList,
    getFightList,
    fightData,
    getFightOne,
    updateFight,
    createData,
    createFight,
    deleteFight,
  };
});
