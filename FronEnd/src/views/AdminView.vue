<template>
    <v-container>
      <v-row>
        <v-col>
          <h4>사용자 목록</h4>
          <v-divider class="my-4"></v-divider>
          <v-data-table
            :headers="headers"
            :items="adminStore.userList"
            item-key="userId"
            class="elevation-1"
          >
            <template v-slot:[`item.delete`]="{ item }">
              <v-btn color="red" @click="deleteUser(item.userId)">
                삭제
              </v-btn>
            </template>
          </v-data-table>
        </v-col>
      </v-row>
    </v-container>
  </template>
  
  <script setup>
  import { useAdminStore } from '@/stores/admin';
  import { onMounted, ref } from 'vue';
  
  const adminStore = useAdminStore();
  const headers = ref([
    { text: '이름', value: 'userName' },
    { text: 'ID', value: 'userId' },
    { text: '닉네임', value: 'nickName' },
    { text: '가입일', value: 'regDate' },
    { text: '유저 삭제', value: 'delete', sortable: false }
  ]);
  
  onMounted(() => {
    adminStore.getUserList();
  });
  
  const deleteUser = (userId) => {
    if (confirm('정말로 삭제하시겠습니까?')) {
      adminStore.deleteUser(userId);
    }
  };
  </script>
  
  <style scoped>
  .v-container {
    padding: 20px;
    max-width: 800px;
    margin: 0 auto;
  }
  
  .v-data-table {
    margin-top: 20px;
  }
  
  .v-btn {
    margin: 0;
  }
  </style>
  