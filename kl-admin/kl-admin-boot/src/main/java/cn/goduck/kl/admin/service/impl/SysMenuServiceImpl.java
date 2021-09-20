package cn.goduck.kl.admin.service.impl;

import cn.goduck.kl.admin.entity.SysMenu;
import cn.goduck.kl.admin.mapper.SysMenuMapper;
import cn.goduck.kl.admin.query.SysMenuQuery;
import cn.goduck.kl.admin.service.SysMenuService;
import cn.goduck.kl.admin.vo.RouteVO;
import cn.goduck.kl.admin.vo.MenuVO;
import cn.goduck.kl.common.core.constant.GlobalConstant;
import cn.goduck.kl.common.core.vo.TreeVO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
@Service
@SuppressWarnings("unchecked")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    @Cacheable(value = "admin", key = "'routeList'")
    public List<RouteVO> routeList() {
        List<SysMenu> menuList = this.baseMapper.menuList();
        return recursionRoute(GlobalConstant.MENU_ROOT_ID, menuList);
    }

    /**
     * 递归生成菜单路由层级列表
     *
     * @param parentId 父级id
     * @param menuList 菜单列表
     * @return 菜单路由层级列表
     */
    private List<RouteVO> recursionRoute(Long parentId, List<SysMenu> menuList) {
        List<RouteVO> menuRouteList = new ArrayList<>();
        Optional.ofNullable(menuList).ifPresent(menus -> menus
                .stream()
                .filter(menu -> parentId.equals(menu.getParentId()))
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
                    menuRouteList.add(routeVO);
                }));
        return menuRouteList;
    }

    @Override
    public List<TreeVO> selectList() {
        List<SysMenu> menuList = this.list(new LambdaQueryWrapper<SysMenu>().orderByAsc(SysMenu::getSort));
        return recursionSelectList(GlobalConstant.MENU_ROOT_ID, menuList);
    }

    /**
     * 递归生成菜单下拉层级列表
     *
     * @param parentId 父级id
     * @param menuList 菜单列表
     * @return 菜单下拉层级列表
     */
    private static List<TreeVO> recursionSelectList(Long parentId, List<SysMenu> menuList) {
        List<TreeVO> menuSelectList = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(menu -> parentId.equals(menu.getParentId()))
                .forEach(menu -> {
                    TreeVO treeVO = new TreeVO();
                    treeVO.setId(menu.getId());
                    treeVO.setLabel(menu.getName());
                    List<TreeVO> children = recursionSelectList(menu.getId(), menuList);
                    if (CollectionUtil.isNotEmpty(children)) {
                        treeVO.setChildren(children);
                    }
                    menuSelectList.add(treeVO);
                });
        return menuSelectList;
    }

    @Override
    public List<MenuVO> tableList(SysMenuQuery sysMenuQuery) {
        boolean haveName = StrUtil.isNotBlank(sysMenuQuery.getName());
        List<SysMenu> menuList = this.list(new LambdaQueryWrapper<SysMenu>()
                .like(haveName, SysMenu::getName, sysMenuQuery.getName())
                .orderByAsc(SysMenu::getSort));
        if (haveName) {
            return menuList.stream().map(menu -> {
                MenuVO menuVO = new MenuVO();
                BeanUtil.copyProperties(menu, menuVO);
                return menuVO;
            }).collect(Collectors.toList());
        }
        return recursionTableList(GlobalConstant.MENU_ROOT_ID, menuList);
    }

    /**
     * 递归生成菜单表格层级列表
     *
     * @param parentId 父级id
     * @param menuList 菜单列表
     * @return 表格层级列表
     */
    private static List<MenuVO> recursionTableList(Long parentId, List<SysMenu> menuList) {
        List<MenuVO> menuTableList = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .forEach(menu -> {
                    MenuVO menuVO = new MenuVO();
                    BeanUtil.copyProperties(menu, menuVO);
                    List<MenuVO> children = recursionTableList(menu.getId(), menuList);
                    if (CollectionUtil.isNotEmpty(children)) {
                        menuVO.setChildren(children);
                    }
                    menuTableList.add(menuVO);
                });
        return menuTableList;
    }

}