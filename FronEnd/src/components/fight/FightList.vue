<template>
  <div id="app">
    <v-app id="inspire">
      <div class="container">
        <button @click="openFight" class="comment-button">댓글달기</button>
        <div class="table-container">
          <table>
            <thead>
              <tr>
                <th>번호</th>
                <th>댓글</th>
                <th>상대</th>
                <th>시간</th>
                <th>수정</th>
                <th>삭제</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(fight, index) in fightStore.fightList" :key="fight.fightId">
                <td>{{ index + 1 }}</td>
                <td>
                  <span v-if="fight.fighter !== loginUser || !userCheck[index]">{{ fight.content }}</span>
                  <fieldset v-if="userCheck[index]">
                    <input type="text" v-model="fightStore.fightData.content" @keyup.enter="updateFight(index)" />
                    <button @click="updateFight(index)" class="complete-button">완료</button>
                    <button @click="cancelUpdate(index)" class="cancel-button">취소</button>
                  </fieldset>
                </td>
                <td>{{ fight.fighter }}</td>
                <td>{{ fight.fightDay }}</td>
                <td>
                  <button @click="changeTag(index, fight.fightId)" v-if="fight.fighter === loginUser" class="modify-button">수정</button>
                </td>
                <td>
                  <button @click="deleteFight(fight.fightId)" v-if="fight.fighter === loginUser" class="delete-button">삭제</button>
                </td>
              </tr>
              <tr v-if="openBool">
                <td>{{ fightLength + 1 }}</td>
                <td>
                  <fieldset>
                    <input ref="createInput" type="text" v-model="fightStore.createData.content" @keyup.enter="createFight" />
                    <button @click="createFight" class="complete-button">완료</button>
                    <button @click="cancelCreate" class="cancel-button">취소</button>
                  </fieldset>
                </td>
                <td>{{ loginUser }}</td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </v-app>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { useArenaStore } from "@/stores/arena";
import { useFightStore } from "@/stores/fight";
import { useRoute, useRouter } from "vue-router";

const arenaStore = useArenaStore();
const fightStore = useFightStore();
const route = useRoute();
const router = useRouter();

const loginUser = sessionStorage.getItem("user");

const userCheck = ref([]);
const updateIng = ref(false);
const openBool = ref(false);
const fightLength = ref(0);
const createInput = ref(null);

const changeTag = async (index, fightId) => {
  if (!updateIng.value) {
    await fightStore.getFightOne(fightId);
    if (userCheck.value[index] === undefined || !userCheck.value[index]) {
      userCheck.value[index] = true;
    }
    updateIng.value = true;
  }
};

const updateFight = async (index) => {
  if (fightStore.fightData.content === "") {
    alert("댓글을 입력해주세요");
  } else {
    await fightStore.updateFight();
    updateIng.value = false;
    userCheck.value[index] = false;
    fightStore.fightList[index] = fightStore.fightData;
  }
};

const cancelUpdate = function (index) {
  updateIng.value = false;
  userCheck.value[index] = false;
};

const openFight = function () {
  if (!openBool.value) {
    openBool.value = true;
    fightStore.createData.arenaId = route.params.arenaId;
    fightStore.createData.fighter = loginUser;
    setTimeout(() => {
      if (createInput.value) {
        createInput.value.focus();
      }
    }, 0);
  }
};

const deleteFight = async (fightId) => {
  await fightStore.deleteFight(fightId);
  await fightStore.getFightList(route.params.arenaId);
};

const createFight = async () => {
  if (fightStore.createData.content === "") {
    alert("댓글을 입력하세요");
  } else {
    await fightStore.createFight();
    fightStore.createData.content = "";
    openBool.value = false;
    await arenaStore.updateInterest(route.params.arenaId, 3);
    await fightStore.getFightList(route.params.arenaId);
    router.go();
  }
};

const cancelCreate = function () {
  fightStore.createData.content = "";
  openBool.value = false;
};

onMounted(async () => {
  await fightStore.getFightList(route.params.arenaId);
  fightLength.value = fightStore.fightList.length;
});
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.table-container {
  max-width: 100%;
  overflow-x: auto;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th,
td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: center;
}

th {
  background-color: #f2f2f2;
}

tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}

tbody tr:hover {
  background-color: #f1f1f1;
}

button {
  border: 1px solid black;
  margin-right: 3px;
}

.comment-button {
  margin-bottom: 20px;
}

.modify-button,
.delete-button,
.complete-button,
.cancel-button {
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
  background-color: #007bff;
  color: #fff;
  border: none;
  margin-right: 5px;
}

.cancel-button {
  background-color: #dc3545;
}
</style>
