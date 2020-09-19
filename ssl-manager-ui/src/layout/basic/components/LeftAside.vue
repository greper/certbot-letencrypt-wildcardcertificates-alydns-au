<template>
  <a-menu theme="light" mode="inline" v-model:selectedKeys="selectedKeys">
    <a-menu-item v-for="(item,index) of menus" :key="item.path">
      <router-link :to="item.path">
        <user-outlined></user-outlined>
        <span>{{ item.title }}</span>
      </router-link>
    </a-menu-item>

  </a-menu>
</template>
<script>
export default {
  name:'left-aside',
  components: {
  },
  data() {
    return {
      selectedKeys: [0],
      menus:[
        {
          title:'首页',
          icon: 'user-outlined',
          path:'/admin/index'
        },
        {
          title:'域名管理',
          icon: 'user-outlined',
          path:'/admin/domain'
        },
        {
          title:'日志',
          icon: 'user-outlined',
          path:'/admin/log'
        },
        {
          title:'Secret设置',
          icon: 'user-outlined',
          path:'/admin/secret'
        }
      ]
    };
  },

};
</script>
