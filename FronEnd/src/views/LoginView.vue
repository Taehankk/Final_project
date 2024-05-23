<template>
  <v-container class="login-container" fluid>
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="4">
        <v-card>
          <v-card-title>
            <h1>로그인을 하다.</h1>
          </v-card-title>
          <v-card-text>
            <v-form>
              <v-text-field
                v-model="loginData.userId"
                label="ID"
                @keyup.enter="login"
                outlined
              ></v-text-field>
              <v-text-field
                v-model="loginData.password"
                label="PW"
                type="password"
                @keyup.enter="login"
                outlined
              ></v-text-field>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" @click="login">Login</v-btn>
            <v-btn text @click="redirectToSignup">Sign Up</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const userStore = useUserStore();

const loginData = ref({
  userId: "",
  password: "",
});

const login = function () {
  userStore.loginUser(loginData.value);
};

const redirectToSignup = function () {
  router.push("/regist"); // Redirect to the registration page
};
</script>

<style scoped>
.login-container {
  height: 70vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>