<template>
  <a-layout id="components-layout-demo-custom-trigger" :class="{aside_collapsed:collapsed,basic_layout: true}">
    <a-layout-sider theme="light" v-model:collapsed="collapsed" :collapsedWidth="64" :trigger="null" collapsible>
        <div class="logo " >
          <img src="/src/assets/logo.svg"/>
          <div class="title">证书管理器</div>
        </div>
      <sider-menu :collapsed="collapsed" :menus="menus" mode="inline" theme="light"  />
    </a-layout-sider>
    <a-layout class="layout_right">
      <a-layout-header class="header_bar" style="background: #fff; padding: 0">
        <menu-unfold-outlined
            v-if="collapsed"
            class="trigger"
            @click="() => (collapsed = !collapsed)"
        />
        <menu-fold-outlined v-else class="trigger" @click="() => (collapsed = !collapsed)" />


        <right-content :top-menu="settings.layout === 'topmenu'"  :theme="settings.theme" />
      </a-layout-header>
      <a-layout-content
          :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }"
      >
        <router-view></router-view>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script>
import defaultSettings from '/src/config/defaultSettings'
import RightContent from './components/RightContent.vue'
import SiderMenu from './components/SiderMenu.jsx'
import LeftAside from './components/LeftAside.vue'
import menuRouters from '/src/router/MenuRouters.js'

export default {
  components: {
    RightContent,
    SiderMenu,
    LeftAside
  },
  data() {
    return {
      collapsed: false,
      title: defaultSettings.title,
      settings: {
        // 布局类型
        layout: defaultSettings.layout, // 'sidemenu', 'topmenu'
        // 定宽: true / 流式: false
        contentWidth: defaultSettings.layout === 'sidemenu' ? false : defaultSettings.contentWidth === 'Fixed',
        // 主题 'dark' | 'light'
        theme: defaultSettings.navTheme,
        // 主色调
        primaryColor: defaultSettings.primaryColor,
        fixedHeader: defaultSettings.fixedHeader,
        fixSiderbar: defaultSettings.fixSiderbar,
        colorWeak: defaultSettings.colorWeak,

        hideHintAlert: false,
        hideCopyButton: false
      },
      menus:menuRouters
    };
  },
};
</script>
<style lang="less">
.basic_layout {
  .trigger {
    font-size: 18px;
    line-height: 64px;
    padding: 0 24px;
    cursor: pointer;
    transition: color 0.3s;
  }
  .trigger:hover {
    color: #1890ff;
  }
  .ant-layout-sider-collapsed{
    .logo{
      .title{
        opacity:0;
      }
    }
  }
  .header_bar{
    box-shadow: 0 2px 8px #f0f1f2;
  }
  .logo {
    height: 44px;
    background: rgba(255, 255, 255, 0.2);
    margin: 10px;
    display:flex;
    align-items: center;
    img{
      height:100%
    }
    .title{
      font-weight: bold;
      font-size:18px;
      opacity:1;
      display:block;
      width:200px;
      overflow: hidden;
      transition:opacity 1s;
      position: absolute;
      z-index: 0;
      left: 64px;
    }
  }

  .ant-layout-sider-light {
    background-color: #fff;
    box-shadow: 2px 0 8px 0 rgba(29,35,41,.05);
  }
  .layout_right{
    z-index:10;
  }

  .ant-menu-inline-collapsed {
    width: 64px;
  }

  .ant-menu-inline-collapsed > .ant-menu-item,
  .ant-menu-inline-collapsed > .ant-menu-item-group > .ant-menu-item-group-list > .ant-menu-item,
  .ant-menu-inline-collapsed > .ant-menu-item-group > .ant-menu-item-group-list > .ant-menu-submenu > .ant-menu-submenu-title,
  .ant-menu-inline-collapsed > .ant-menu-submenu > .ant-menu-submenu-title {
    padding: 0px 24px !important;
  }

  .ant-layout-header:before{
    content: "";
    height:80%;
    border-left:1px solid #eee
  }



  &.aside_collapsed{
    .ant-layout-header:before{
      border-left:0px
    }
  }

  .ant-pro-global-header-index-right {
    float: right;
    height: 100%;
    margin-left: auto;
    overflow: hidden;
    margin-right: 8px;
    height:100%;
  }
}


</style>
