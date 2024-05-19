import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "@/views/LoginView.vue";
import RegistView from "@/views/RegistView.vue";
import { computed } from "vue";
import AdminView from "@/views/AdminView.vue";
import RankView from "@/views/RankView.vue";
import GameView from "@/views/GameView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
    },
    {
      path: "/regist",
      name: "regist",
      component: RegistView,
    },
    {
      path: "/admin",
      name: "admin",
      component: AdminView,
    },
    {
      path: "/game",
      name: "game",
      component: GameView,
    },
    {
      path: "/rank",
      name: "rank",
      component: RankView,
    }
  ],
});

router.beforeEach((to, from) => {
  const user = sessionStorage.getItem("user");

  if(user && (to.name === 'login' | to.name === 'regist')){
    return { name: 'home'}
  }

  if(user !== "admin" && (to.name === 'admin')){
    return { name: 'home'}
  }
})

export default router;
