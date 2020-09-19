import IndexView from "../views/index/index.vue";
import DomainView from "../views/domain/index.jsx";
import BlankLayout from "../layout/blank/index.vue";

export default [
    {
        name: 'index',
        path: '/admin/index',
        meta: {
            title: '首页',
            auth: true,
            icon: 'UserOutlined',
        },
        component: IndexView,
    },
    {
        name: 'domain',
        path: '/admin/domain',
        meta: {
            title: '域名管理',
            auth: true,
            icon: 'UserOutlined',
        },
        component: DomainView,
    },
    {
        name: 'other',
        path: '/admin/other',
        meta: {
            title: '其他管理',
            auth: true,
            icon: 'UserOutlined',
        },
        component: BlankLayout,
        children: [
            {
                name: 'log',
                path: '/admin/log',
                meta: {
                    title: '日志管理',
                    auth: true,
                    icon: 'UserOutlined',
                },
                component: DomainView,
            },
            {
                name: 'setting',
                path: '/admin/setting',
                meta: {
                    title: '系统设置',
                    auth: true,
                    icon: 'UserOutlined',
                },
                component: IndexView,
            },
        ]
    }
]