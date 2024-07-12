import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useRouter } from "vue-router";

const REST_ARENA_API = `http://localhost:8080/ansmoon/arena`;
export const useArenaStore = defineStore("arena", () => {
  const arenaList = ref([]);
  const arena = ref({});
  const router = useRouter();

  const getArenaList = function () {
    axios.get(`${REST_ARENA_API}/list`).then((response) => {
      arenaList.value = response.data;
    });
  };

  const getArenaNow = async (id) => {
    await axios.get(`${REST_ARENA_API}/detail/${id}`).then((response) => {
      arena.value = response.data;
    });
  };

  const createArena = async (arena) => {
    await axios.post(`${REST_ARENA_API}/regist`, arena).then(() => {
      alert("등록 완료");
    });
  };

  const deleteArena = async (id) => {
    await axios.delete(`${REST_ARENA_API}/delete?id=${id}`).then(() => {
      alert("삭제 완료");
    });
  };

  const updateArena = async (arena) => {
    await axios.put(`${REST_ARENA_API}/update`, arena).then(() => {
      alert("수정 완료");
    });
  };

  const updateInterest = async (id, cnt) => {
    await axios.get(`${REST_ARENA_API}/interest?id=${id}&cnt=${cnt}`);
  };

  return {
    arenaList,
    getArenaList,
    arena,
    getArenaNow,
    createArena,
    deleteArena,
    updateArena,
    updateInterest,
  };
});
