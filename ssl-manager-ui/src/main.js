import { createApp } from 'vue';
import App from './App.vue';
import './index.css';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import router from './router';
import store from './store/index.js';
import icons from './components/icons';

const app = createApp(App);
app.use(Antd);
app.use(store);
for (let key in icons) {
  app.component(key, icons[key]);
}
app.use(router);

app.config.productionTip = false;
app.mount('#app');
