import { createApp } from 'vue'
import App from './App.vue'
import './index.css'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import router from "./router";
import store from './store/index.js'
const app = createApp(App)
app.use(Antd)
app.use(router)
app.use(store)
app.config.productionTip = false;
app.mount('#app')
