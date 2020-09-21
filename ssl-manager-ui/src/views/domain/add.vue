<template>
  <div>
    <a-steps :current="current">
      <a-step v-for="item in steps" :key="item.title" :title="item.title" :description="item.description"/>
    </a-steps>

    <div class="steps-content">
      <a-form ref="form" :model="form" :rules="rules" :wrapperCol="{span:10,offset:7}">
        <div v-if="current === 0">

          <a-form-item name="domain">
            <a-input v-model:value="form.domain" size="large"
                     placeholder="请输入要绑定证书的泛域名，例如：*.docmirror.cn">
              <template v-slot:prefix>
                <UserOutlined style="color:rgba(0,0,0,.25)"/>
              </template>
            </a-input>
          </a-form-item>

        </div>
        <div v-if="current === 1">
          <a-form-item name="owner.email" placeholder="邮箱" >
            <a-input v-model:value="form.owner.email" placeholder="邮箱">
              <template v-slot:prefix>
                <UserOutlined style="color:rgba(0,0,0,.25)"/>
              </template>
            </a-input>
          </a-form-item>
        </div>
      </a-form>
    </div>
    <div class="steps-action">
      <a-button v-if="current > 0" style="margin-right: 8px" @click="prev">
        上一步
      </a-button>
      <a-button v-if="current < steps.length - 1" type="primary" @click="next">
        下一步
      </a-button>
      <a-button
          v-if="current === steps.length - 1"
          type="primary"
          @click="$message.success('Processing complete!')"
      >
        完成
      </a-button>
    </div>

  </div>
</template>
<script>

import {reactive} from 'vue'

function domainFormSetup() {
}

export default {
  data() {
    return {
      current: 0,
      form: {
        domain: '*.docmirror.com',
        provider: {},
        email:'',
        owner:{email:'xiaojunnuo@qq.com'},
      },
      rules: {
        domain: [
          {required: true, message: '请填写域名'},
          {pattern: /^\*(\.[^.\W]+)+$/, message: "泛域名必须符合*.xxx.yyy格式"}
        ],
        owner:{
          email:[
            {required: true, message: '请填写邮箱'},
            {type:'email',message:'请输入正确的邮箱'}
          ],
        },
        'owner.email':[
            {required: true, message: '请填写邮箱'},
            {type:'email',message:'请输入正确的邮箱'}
          ],
      },
      steps: [
        {
          title: '域名',
          description: '请输入泛域名',
        },
        {
          title: '所有人信息',
          description: '域名所有人信息',
        },
        {
          title: '域名提供商',
          content: 'Last-content',
        },
        {
          title: '注册信息',
          content: 'Second-content',
        },
      ],
    };
  },
  setup() {
    const domain = domainFormSetup()

    return {...domain}
  },
  methods: {
    next() {
      this.$refs.form
          .validate()
          .then(() => {
            console.log(111)
            this.current++;
          })
    },
    prev() {
      this.current--;
    },
  },
}
</script>

<style scoped>
.steps-content {
  margin-top: 16px;
  border: 1px dashed #e9e9e9;
  border-radius: 6px;
  background-color: #fafafa;
  min-height: 200px;
  padding-top: 80px;
}

.steps-action {
  text-align: center;
  margin-top: 24px;
}
</style>