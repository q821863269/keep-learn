package cn.goduck.kl.admin.service.impl;

import cn.goduck.kl.admin.entity.SysMenu;
import cn.goduck.kl.admin.mapper.SysMenuMapper;
import cn.goduck.kl.admin.service.SysMenuService;
import cn.goduck.kl.admin.vo.RouteVO;
import cn.goduck.kl.admin.vo.SysMenuVO;
import cn.goduck.kl.common.core.constant.GlobalConstant;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    @Cacheable(value = "admin", key = "'routerList'")
    public List<RouteVO> routeList() {
        List<SysMenuVO> menuList = this.baseMapper.menuList();
        return recursionRoute(GlobalConstant.MENU_ROOT_ID, menuList);
    }

    /**
     * 递归生成菜单路由层级列表
     *
     * @param parentId 父级ID
     * @param menuList 菜单列表
     * @return 菜单路由层级列表
     */
    private List<RouteVO> recursionRoute(Long parentId, List<SysMenuVO> menuList) {
        List<RouteVO> list = new ArrayList<>();
        Optional.ofNullable(menuList).ifPresent(menus -> menus.stream().filter(menu -> parentId.equals(menu.getParentId()))
                .forEach(menu -> {
                    RouteVO routeVO = new RouteVO();
                    routeVO.setPath(menu.getRoutePath());
                    routeVO.setComponent(menu.getComponent());
                    routeVO.setRedirect(menu.getRedirect());
                    routeVO.setName(menu.getRouteName());
                    RouteVO.Meta meta = new RouteVO.Meta(menu.getName(), menu.getIcon(), menu.getRoles());
                    routeVO.setMeta(meta);
                    routeVO.setHidden(menu.getVisible());
                    List<RouteVO> children = recursionRoute(menu.getId(), menuList);
                    routeVO.setChildren(children);
                    if (CollectionUtil.isNotEmpty(children)) {
                        routeVO.setAlwaysShow(Boolean.TRUE);
                    }
                    list.add(routeVO);
                }));
        return list;
    }

}