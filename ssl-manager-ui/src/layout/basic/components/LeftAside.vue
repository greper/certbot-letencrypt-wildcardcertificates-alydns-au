<template>
  <a-menu theme="light" mode="inline" v-model:selectedKeys="selectedKeys">
    <a-menu-item v-for="(item,index) of menus" :key="item.path">
      <user-outlined></user-outlined>
      <span>{{ item.title }}</span>
    </a-menu-item>

    <a-sub-menu>
      <template v-slot:title>
        <span><MailOutlined /><span>Navigation One</span></span>
      </template>
      <a-menu-item v-for="(item,index) of menus" :key="item.path">
        <user-outlined></user-outlined>
        <span>{{ item.title }}</span>
      </a-menu-item>
    </a-sub-menu>
  </a-menu>
</template>
<script>
import {
  UserOutlined,
  VideoCameraOutlined,
  UploadOutlined,
  MenuUnfoldOutlined,
  MenuFoldOutlined,
} from '@ant-design/icons-vue';
export default {
  name:'left-aside',
  components: {
    UserOutlined,
    VideoCameraOutlined,
    UploadOutlined,
    MenuUnfoldOutlined,
    MenuFoldOutlined,
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
