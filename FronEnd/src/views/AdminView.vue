<template>
    <div>
        <h4>사용자 목록</h4>
        <hr>
        <table>
            <tr>
                <th>이름</th>
                <th>ID</th>
                <th>닉네임</th>
                <th>가입일</th>
                <th>유저 삭제</th>
            </tr>
            <tr v-for="user in adminStore.userList" :key="user.userId">
                <td>{{ user.userName }}</td>
                <td>{{ user.userId }}</td>
                <td>{{ user.nickName }}</td>
                <td>{{ user.regDate }}</td>
                <td>
                    <button @click="deleteUser(user.userId)">삭제</button>
                </td>
            </tr>
        </table>
    </div>
</template>

<script setup>
import { useAdminStore } from '@/stores/admin';
import { onMounted } from 'vue';

const adminStore = useAdminStore();

onMounted(() => {
    adminStore.getUserList();
})

const deleteUser = function (userId) {
    adminStore.deleteUser(userId); 
}
</script>

<style scoped>

</style>