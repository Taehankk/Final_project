import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "@/views/LoginView.vue";
import RegistView from "@/views/RegistView.vue";
import AdminView from "@/views/AdminView.vue";
import RankView from "@/views/RankView.vue";
import GameView from "@/views/GameView.vue";
import KakaoView from "@/views/KakaoView.vue";
import ArenaView from "@/views/ArenaView.vue";
import ArenaList from "@/components/arena/ArenaList.vue";
import ArenaDetail from "@/components/arena/ArenaDetail.vue";
import ArenaCreate from "@/components/arena/ArenaCreate.vue";
import ArenaUpdate from "@/components/arena/ArenaUpdate.vue";

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
    },
    {
      path: "/arena",
      name: "arena",
      component: ArenaView,
      children: [
        {
          path: "",
          name: "arenaList",
          component: ArenaList,
        },
        {
          path: "detail/:arenaId",
          name: "arenaDetail",
          component: ArenaDetail,
        },
        {
          path: "create",
          name: "createArena",
          component: ArenaCreate,
        },
        {
          path: "update",
          name: "updateArena",
          component: ArenaUpdate,
        },
      ],
    },
    {
      path: "/kakao",
      name: "kakao",
      component: KakaoView,
    },
  ],
});

router.beforeEach((to, from) => {
  const user = sessionStorage.getItem("user");

  if (user && (to.name === "login") | (to.name === "regist")) {
    return { name: "home" };
  }

  if (user !== "admin" && to.name === "admin") {
    return { name: "home" };
  }

  if (!user && to.name === "arenaDetail") {
    return { name: "arenaList" };
  }

  if (!user && to.name === "game") {
    return { name: "home" };
  }

  if (!user && to.name === "createArena") {
    return { name: "arenaList" };
  }
});

export default router;
