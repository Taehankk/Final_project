<template>
  <div id="app">
    <v-app id="inspire">
      <div class="comment-section">
        <div class="comment-input-container">
          <textarea
            v-model="fightStore.createData.content"
            ref="createInput"
            placeholder="댓글을 입력하세요"
            class="comment-input"
          ></textarea>
          <button @click="createFight" class="submit-button">댓글달기</button>
        </div>
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
              <tr
                v-for="(fight, index) in fightStore.fightList"
                :key="fight.fightId"
              >
                <td>{{ index + 1 }}</td>
                <td>
                  <span
                    v-if="fight.fighter !== loginUser || !userCheck[index]"
                    >{{ fight.content }}</span
                  >
                  <fieldset v-if="userCheck[index]">
                    <input
                      ref="updateInput"
                      type="text"
                      v-model="fightStore.fightData.content"
                      @keyup.enter="updateFight(index)"
                    />
                    <button @click="updateFight(index)" class="complete-button">
                      완료
                    </button>
                    <button @click="cancelUpdate(index)" class="cancel-button">
                      취소
                    </button>
                  </fieldset>
                </td>
                <td>{{ fight.fighter }}</td>
                <td>{{ fight.fightDay }}</td>
                <td>
                  <button
                    @click="changeTag(index, fight.fightId)"
                    v-if="fight.fighter === loginUser"
                    class="modify-button"
                  >
                    수정
                  </button>
                </td>
                <td>
                  <button
                    @click="deleteFight(fight.fightId)"
                    v-if="fight.fighter === loginUser"
                    class="delete-button"
                  >
                    삭제
                  </button>
                </td>
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
import { useFightStore } from "@/stores/fight";
import { useRoute, useRouter } from "vue-router";
import { useArenaStore } from "@/stores/arena";

const fightStore = useFightStore();
const arenaStore = useArenaStore();
const route = useRoute();
const router = useRouter();

const loginUser = sessionStorage.getItem("user");

const userCheck = ref([]);
const updateIng = ref(false);
const fightLength = ref(0);
const createInput = ref(null);
const updateInput = ref(null);

const changeTag = async (index, fightId) => {
  if (!updateIng.value) {
    await fightStore.getFightOne(fightId);
    setTimeout(() => {
      if (updateInput.value[0]) {
        updateInput.value[0].focus();
      }
    }, 0);
    userCheck.value[index] = true;
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

const cancelUpdate = (index) => {
  updateIng.value = false;
  userCheck.value[index] = false;
};

const createFight = async () => {
  if (fightStore.createData.content === "") {
    alert("댓글을 입력하세요");
  } else {
    fightStore.createData.arenaId = route.params.arenaId;
    await fightStore.createFight();
    fightStore.createData.content = "";
    await arenaStore.updateInterest(route.params.arenaId, 3);
    await fightStore.getFightList(route.params.arenaId);
    router.go();
  }
};

const deleteFight = async (fightId) => {
  await fightStore.deleteFight(fightId);
  await fightStore.getFightList(route.params.arenaId);
};

onMounted(async () => {
  await fightStore.getFightList(route.params.arenaId);
  fightLength.value = fightStore.fightList.length;
});
</script>

<style scoped>
.comment-section {
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.comment-input-container {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.comment-input {
  flex: 1;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  margin-right: 10px;
  font-size: 16px;
}

.submit-button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.submit-button:hover {
  background-color: #0056b3;
}

.table-container {
  width: 100%;
  overflow-x: auto;
}

table {
  width: 100%;
  border-collapse: collapse;
  background-color: #fff;
}

th,
td {
  padding: 10px;
  border: 1px solid #eaeaea;
  text-align: left;
}

th {
  background-color: #f2f2f2;
}

button {
  padding: 5px 10px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #218838;
}

button.cancel-button {
  background-color: #dc3545;
}

button.cancel-button:hover {
  background-color: #c82333;
}

fieldset {
  display: flex;
  align-items: center;
  padding: 0;
  margin: 0;
  border: none;
}

input[type="text"] {
  flex: 1;
  padding: 5px 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  margin-right: 5px;
}
</style>