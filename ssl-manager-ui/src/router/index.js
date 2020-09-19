import { createRouter, createWebHistory } from 'vue-router';
import routerList from './routers';
const router = createRouter({
  history: createWebHistory(),
  routes: routerList,
});

export default router;
