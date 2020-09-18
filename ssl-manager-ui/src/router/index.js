import { createRouter, createWebHistory } from 'vue-router'
import BasicLayout from '../layout/basic/index.vue'
import UserLayout from '../layout/user/index.vue'
import LoginView from '../views/login/index.vue'
import IndexView from '../views/index/index.vue'


const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      redirect: { name: 'index' },
    },
    {
      path: '/admin',
      component: BasicLayout,
      redirect: { name: 'index' },
      children:[
        // 首页
        {
          path: 'index',
          name: 'index',
          meta: {
            title: '首页',
            auth: true
          },
          component: IndexView
        },
      ]
    },
    {
      path: '/user',
      component: UserLayout,
      redirect: { name: 'login' },
      children:[
        // 首页
        {
          path: 'login',
          name: 'login',
          meta: {
            title: '登录',
            auth: true
          },
          component: LoginView
        },
      ]
    }
  ]
})

export default router
