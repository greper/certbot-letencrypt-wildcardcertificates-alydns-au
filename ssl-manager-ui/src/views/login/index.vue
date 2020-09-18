<template>
  <div class="login_body">
    <div class="box">
      <h2 class="title">登录</h2>

      <a-form
          ref="form"
          :model="form"
          :rules="rules"
      >
        <a-form-item ref="username" name="username">
          <a-input v-model:value="form.username"  placeholder="用户名" size="large">
            <template v-slot:prefix><UserOutlined style="color:rgba(0,0,0,.25)"/></template>
          </a-input>
        </a-form-item>
        <a-form-item>
          <a-input v-model:value="form.password" type="password" placeholder="密码" size="large">
            <template v-slot:prefix><LockOutlined style="color:rgba(0,0,0,.25)"/></template>
          </a-input>
        </a-form-item>

        <a-form-item >
          <a-button type="primary" @click="onSubmit" size="large" class="login_button" :loading="logining" :disabled="logining">
            登录
          </a-button>
        </a-form-item>
      </a-form>

    </div>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
import { UserOutlined, LockOutlined } from '@ant-design/icons-vue';
import {timeFix} from '/src/utils/util'

export default {
  components: {
    UserOutlined,
    LockOutlined,
  },
  data() {
    return {
      labelCol: {span: 4},
      wrapperCol: {span: 14},
      logining: false,
      form: {
        username: undefined,
        password: undefined
      },
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 20, message: '长度必须在 3 到 20 之间', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 20, message: '长度必须在 6 到 20 之间', trigger: 'blur'},
        ],
      },
    }
  },
  methods: {
    ...mapActions(['Login', 'Logout']),
    onSubmit() {
      const {
        Login
      } = this
      const loginParams = { ...this.form }
      console.log('mapActions',mapActions,mapActions(['Login', 'Logout']))
      this.$refs.form
          .validate()
          .then(() => {
            this.logining=true
            return Login(loginParams)
          }).then((res) => this.loginSuccess(res))
          .catch(err => this.requestFailed(err))
          .finally(() => {
            this.logining = false
          })
    },
    loginSuccess (res) {
      console.log(res)
      this.$router.push({ path: '/admin/' })
      // 延迟 1 秒显示欢迎信息
      setTimeout(() => {
        this.$notification.success({
          message: '欢迎',
          description: `${timeFix()}，欢迎回来`
        })
      }, 1000)
      this.isLoginError = false
    },
    requestFailed (err) {
      console.error(err)
      this.isLoginError = true
      this.$notification['error']({
        message: '错误',
        description: ((err.response || {}).data || {}).message || '请求出现错误，请稍后再试',
        duration: 4
      })
    }
  }
}
</script>

<style lang="less" scoped>
.login_body {
  .box {
    margin:auto;
    width: 350px;
    .title{
      text-align: center;
    }
    .login_button {
      width: 100%;
    }
  }

}
</style>
