import PropTypes from 'ant-design-vue/es/_util/vue-types/index.js';
import { Menu, Icon } from 'ant-design-vue';
import { ref, watch, toRefs } from 'vue';
const { Item: MenuItem, SubMenu } = Menu;
import { useRouter, useRoute } from 'vue-router';
import icons from '/src/components/icons'
export const RouteMenuProps = {
    menus: PropTypes.array,
    theme: PropTypes.string.def('dark'),
    mode: PropTypes.string.def('inline'),
    collapsed: PropTypes.bool.def(false),
    i18nRender: PropTypes.oneOfType([PropTypes.func, PropTypes.bool]).def(false),
};

const renderMenu = (h, item) => {
    if (item && !item.hidden) {
        const bool = item.children && !item.hideChildrenInMenu;
        return bool ? renderSubMenu(h, item) : renderMenuItem(h, item);
    }
    return null;
};

const renderSubMenu = (h, item) => {
    const defaultRender = (h) => {
        return !item.hideChildrenInMenu && item.children.map((cd) => renderMenu(h, cd));
    };
    return (
        <SubMenu
            key={item.path}
            title={
                <span>
                    {renderIcon(h, item.meta.icon)}
                    <span>{renderTitle(h, item.meta.title)}</span>
                </span>
            }
        >
            {defaultRender}
        </SubMenu>
    );
};

const renderMenuItem = (h, item) => {
    if (item.children && item.hideChildrenInMenu) {
        // 把有子菜单的 并且 父菜单是要隐藏子菜单的
        // 都给子菜单增加一个 hidden 属性
        // 用来给刷新页面时， selectedKeys 做控制用
        item.children.forEach((cd) => {
            cd.meta = Object.assign(cd.meta || {}, { hidden: true });
        });
    }
    const defaultRender = (h)=>{
        return [
            <span>
                {renderIcon(h, item.meta.icon)}
                {renderTitle(h, item.meta.title)}
            </span>
        ]
    }
    return (
        <MenuItem key={item.path}>
            {defaultRender}
        </MenuItem>
    );

};
const renderIcon = (h, icon) => {
    const Icon = icons[icon];
    if(Icon == null){
        return icons['UserOutlined'];
    }
    return <Icon ></Icon>;
};

const renderTitle = (h, title, i18nRender) => {
    return <span>{(i18nRender && i18nRender(title)) || title}</span>;
};

const RouteMenu = {
    name: 'RouteMenu',
    props: RouteMenuProps,
    setup(props, context) {
        const router = useRouter();
        const route = useRoute();
        const { collapsed, mode } = toRefs(props);
        const openKeys = ref([]);
        const cachedOpenKeys = ref([]);
        const selectedKeys = ref([]);
        console.log('on created', props, context);
        // const watch1 = watch('$route', () => {
        //   this.updateMenu();
        // });

        function updateMenu() {
            console.log('updateMenu------------');
            const routes = route.matched.concat();
            const { hidden } = route.meta;
            if (routes.length >= 3 && hidden) {
                routes.pop();
                selectedKeys.value = [routes[routes.length - 1].path];
            } else {
                selectedKeys.value = [routes.pop().path];
            }
            console.log('selectedKeys',selectedKeys.value)
        }
        watch(route, () => {
            updateMenu();
        });


        watch(selectedKeys, (val) => {
            console.log('selectedKeys', val);
        });
        updateMenu();

        const goRouterLink = (item)=>{
            console.log('go router link',item)
            if(item.path){
                if(item.name){
                    router.push({name:item.name})
                }else if(item.fullpath){
                    router.push(item.fullpath)
                }
            }
        }
        return { openKeys, cachedOpenKeys, updateMenu, selectedKeys,goRouterLink };
    },
    render(h) {
        const { mode, theme, menus, collapsed,selectedKeys,openKeys } = this;

        const menuItemsRender = (h) => {
            const menuItems = menus.map((item) => {
                if (item.hidden) {
                    return null;
                }
                return renderMenu(h, item);
            });
            return menuItems;
        };
        const router = useRouter();
        const route = useRoute();
        const props = {
            mode:mode,
            theme:theme,
            selectedKeys:selectedKeys,
            openKeys:openKeys,
            'onUpdate:selectedKeys': ($event)=>{
                console.log('selectedKeys',$event)
                this.selectedKeys = $event
            },
            'onUpdate:openKeys':($event)=>{
                console.log('openKeys',$event)
                this.openKeys = $event
            },
            'onSelect':(item, key, selectedKeys)=>{
                console.log('onSelect',item,key,selectedKeys)
                router.push(item.key)
            }
        }
        return (
            <Menu  {...props}>
                {menuItemsRender}
            </Menu>
        );
    },


};

export default RouteMenu;
