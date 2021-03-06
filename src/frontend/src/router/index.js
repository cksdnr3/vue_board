import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '../views/Home.vue'
import BoardListPage from '../views/BoardListPage.vue'
import BoardRegisterPage from '../views/BoardRegisterPage.vue'
import BoardModifyPage from '../views/BoardModifyPage.vue'
import BoardReadPage from '../views/BoardReadPage.vue'
import Test from "@/views/Test";
import VuetifyBoardListPage from "@/views/vuetifyPage/VuetifyBoardListPage";
import VuetifyBoardListPaginationPage from "@/views/vuetifyPage/VuetifyBoardListPaginationPage";
import CrawlCategoryPage from "@/views/crawl/CrawlCategoryPage";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/test',
    name: 'Test',
    component: Test
  },
  {
    path: '/board',
    name: 'BoardListPage',
    components: {
      default: BoardListPage
    }
  },
  {
    path: '/board/create',
    name: 'BoardRegisterPage',
    components: {
      default: BoardRegisterPage
    }
  },
  {
    path: '/board/:boardNo',
    name: 'BoardReadPage',
    components: {
      default: BoardReadPage
    },
    props: {
      default: true
    }
  },
  {
    // class -> obj,
    path: '/board/:boardNo/edit',
    name: 'BoardModifyPage',
    components: {
      default: BoardModifyPage
    },
    props: {
      // default true 면 params를 보낼 수 있음
      default: true
    }
  },
  {
    path: '/vuetifyBoard',
    name: 'VuetifyBoardListPage',
    components: {
      default: VuetifyBoardListPage
    }
  },
  {
    path: '/vuetifyPagination',
    name: 'VuetifyBoardListPaginationPage',
    components: {
      default: VuetifyBoardListPaginationPage
    }
  },
  {
    path: '/crawlCategory',
    name: 'CrawlCategoryPage',
    components: {
      default: CrawlCategoryPage
    }
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
