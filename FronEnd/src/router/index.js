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
import FightList from "@/components/fight/FightList.vue";

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
      path: "/game/:category",
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
          children: [
            {
              path: "",
              name: "fight",
              component: FightList,
            },
          ],
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

import { ref } from "vue";

router.beforeEach((to, from, next) => {
  const user = ref(sessionStorage.getItem("user"));

  if (user.value && (to.name === "login" || to.name === "regist")) {
    // 사용자가 로그인 상태이면서 로그인 또는 회원가입 페이지로 이동하려고 할 때
    alert("이미 로그인되어 있습니다.");
    next({ name: "home" });
  } else if (user.value !== "admin" && to.name === "admin") {
    // 사용자가 관리자가 아닌데 관리자 페이지로 이동하려고 할 때
    alert("이곳은 관리자만 갈 수 있습니다.");
    next({ name: "home" });
  } else if (!user.value && to.name === "fight") {
    // 사용자가 로그인하지 않은 상태에서 전투 페이지로 이동하려고 할 때
    alert("로그인 필수로 하다.");
    next({ name: "arenaList" });
  } else if (!user.value && to.name === "game") {
    // 사용자가 로그인하지 않은 상태에서 게임 페이지로 이동하려고 할 때
    alert("로그인 필수로 하다.");
    next({ name: "home" });
  } else if (!user.value && to.name === "createArena") {
    // 사용자가 로그인하지 않은 상태에서 아레나 생성 페이지로 이동하려고 할 때
    alert("로그인 필수로 하다.");
    next({ name: "arenaList" });
  } else {
    // 조건에 해당하지 않는 경우 정상적으로 다음 페이지로 이동
    next();
  }
});

export default router;
