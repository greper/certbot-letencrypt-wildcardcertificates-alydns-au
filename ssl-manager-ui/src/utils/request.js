import axios from 'axios'
import store from '/src/store'
import storage from 'store'
import { VueAxios } from './axios'
import { ACCESS_TOKEN } from '/src/store/mutation-types'
import Antd from 'ant-design-vue';

console.log('vite_api',import.meta.env.VITE_API)
const notification = Antd.notification
// 创建 axios 实例
const request = axios.create({
  // API 请求的默认前缀
  baseURL: import.meta.env.VITE_API,
  timeout: 6000 // 请求超时时间
})

// 异常拦截处理器
const errorHandler = (error) => {
  if (error.response) {
    const data = error.response.data
    // 从 localstorage 获取 token
    const token = storage.get(ACCESS_TOKEN)
    if (error.response.status === 403) {
      notification.error({
        message: 'Forbidden',
        description: data.message
      })
    }
    if (error.response.status === 401 ) {
      notification.error({
        message: 'Unauthorized',
        description: 'Authorization verification failed'
      })
      if (token) {
        store.dispatch('Logout').then(() => {
          setTimeout(() => {
            window.location.reload()
          }, 1500)
        })
      }
    }
  }
  notification.error({
    message: '请求错误',
    description: error.message
  })
  console.error(error)
  return Promise.reject(error)
}

// request interceptor
request.interceptors.request.use(config => {
  const token = storage.get(ACCESS_TOKEN)
  // 如果 token 存在
  // 让每个请求携带自定义 token 请根据实际情况自行修改
  if (token) {
    config.headers['Access-Token'] = token
  }
  return config
}, errorHandler)

// response interceptor
request.interceptors.response.use((response) => {
  const ret = response.data;
  console.log('response',response)
  if(!ret){
    notification.error({
      message: '未知错误',
      description: '请求没有返回结果'
    })
    return
  }else{
    if( ret.code ===0){
      return ret
    }else if(ret.code === 1){
      //返回错误
      notification.error({
        message: '请求错误',
        description: ret.msg
      })
      return;
    }
  }
  return ret
}, errorHandler)

const installer = {
  vm: {},
  install (Vue) {
    Vue.use(VueAxios, request)
  }
}

export default request

export {
  installer as VueAxios,
  request as axios
}
