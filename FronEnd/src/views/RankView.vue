<template>
    <div>        
        <div>
            <h4>사용자 랭킹</h4>
            <label>정렬 기준 : </label>
            <select v-model="orderInfo.orderSt">
                <option value="score">점수</option>
                <option value="solveDate">달성일</option>
            </select>
            <label>정렬 방향 : </label>
            <select v-model="orderInfo.orderByDir">
                <option value="asc">오름차순</option>
                <option value="desc">내림차순</option>
            </select>
        </div>
        <hr>
        <table>
            <tr>
                <th>이름</th>
                <th>ID</th>
                <th>닉네임</th>
                <th>점수</th>
                <th>달성일</th>
            </tr>
            <tr v-for="data in rankStore.dataList" :key="data.id">
                <td>{{ data.userName }}</td>
                <td>{{ data.userId }}</td>
                <td>{{ data.nickName }}</td>
                <td>{{ data.score }}</td>
                <td>{{ data.solveDate }}</td>
            </tr>
        </table>
        
    </div>
</template>

<script setup>
import { useRankStore } from '@/stores/rank';
import { ref, onMounted, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const router = useRouter();

const rankStore = useRankStore();

const orderInfo = ref({
    orderSt: 'score',
    orderByDir: 'desc',
})

watch(() => orderInfo.value.orderSt, (nv, ov) => {
    if(nv !== ov){
        rankStore.getDataList(orderInfo.value);
    }
})

watch(() => orderInfo.value.orderByDir, (nv, ov) => {
    if(nv !== ov){
        rankStore.getDataList(orderInfo.value)
    }
})

onMounted(() => {

    console.log(rankStore.dataList.value);
    rankStore.getDataList(orderInfo.value);
})
</script>

<style scoped>

</style>