<template>
  <div>
    <a-steps :current="current">
      <a-step v-for="item in steps" :key="item.title" :title="item.title" :description="item.description" />
    </a-steps>
    <div class="steps-content">
      <div v-if="current === 0">

        <a-form layout="inline" :model="form" >
          <a-form-item>
            <a-input v-model:value="form.domain" placeholder="请输入要绑定证书的泛域名，例如：*.docmirror.cn">
              <template v-slot:prefix><UserOutlined style="color:rgba(0,0,0,.25)"/></template>
            </a-input>
          </a-form-item>
        </a-form>
      </div>
    </div>
    <div class="steps-action">
      <a-button v-if="current > 0" style="margin-right: 8px" @click="prev">
        上一步
      </a-button>
      <a-button v-if="current < steps.length - 1" type="primary" @click="next">
        下一步
      </a-button>
      <a-button
          v-if="current == steps.length - 1"
          type="primary"
          @click="$message.success('Processing complete!')"
      >
        完成
      </a-button>

    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      current: 0,
      form:{
        domain:undefined,
        provider:{

        }
      },
      steps: [
        {
          title: '域名',
          description:'请输入泛域名',
          content: 'First-content',
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
  methods: {
    next() {
      this.current++;
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
  text-align: center;
  padding-top: 80px;
}

.steps-action {
  text-align: center;
  margin-top: 24px;
}
</style>