import BasicLayout from '../layout/basic/index.vue';
import UserLayout from '../layout/user/index.vue';
import LoginView from '../views/login/index.vue';
import menuRouters from './MenuRouters.js'
const routers = [
  {
    path: '/',
    redirect: { name: 'index' },
  },
  {
    path: '/admin',
    component: BasicLayout,
    redirect: { name: 'index' },
    meta: {
      title: '管理页面',
    },
    children: menuRouters
  },
  {
    path: '/user',
    component: UserLayout,
    redirect: { name: 'login' },
    meta: {
      title: '用户管理',
    },
    children: [
      // 首页
      {
        path: 'login',
        name: 'login',
        meta: {
          title: '登录',
          auth: true,
        },
        component: LoginView,
      },
    ],
  },
];
export default routers;
