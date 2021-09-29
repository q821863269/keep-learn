package cn.goduck.kl.admin.service.impl;

import cn.goduck.kl.admin.service.SysPermissionService;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.goduck.kl.admin.mapper.SysRoleMenuMapper;
import cn.goduck.kl.admin.entity.SysRoleMenu;
import cn.goduck.kl.admin.service.SysRoleMenuService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
@Service
@AllArgsConstructor
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    private final SysPermissionService sysPermissionService;

    @Override
    public List<Long> menuIdList(Long roleId) {
        return this.baseMapper.menuIdList(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRoleMenu(Long roleId, List<Long> menuIdList) {
        boolean result = true;
        List<SysRoleMenu> roleMenuList = this.list(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));
        List<Long> dbMenuIdList = roleMenuList.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());

        // 删除数据库有，menuIdList没有的
        if (CollectionUtil.isNotEmpty(dbMenuIdList)) {
            List<Long> removeMenuIdList = dbMenuIdList.stream().filter(id -> !menuIdList.contains(id)).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(removeMenuIdList)) {
                this.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId)
                        .in(SysRoleMenu::getMenuId, removeMenuIdList));
            }
        }

        // 删除数据库不存在的
        if (CollectionUtil.isNotEmpty(menuIdList)) {
            List<Long> insertMenuIdList = menuIdList.stream().filter(id -> !menuIdList.contains(id)).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(insertMenuIdList)) {
                List<SysRoleMenu> sysRoleMenuList = new ArrayList<>();
                for (Long menuId : insertMenuIdList) {
                    SysRoleMenu sysRoleMenu = new SysRoleMenu();
                    sysRoleMenu.setRoleId(roleId);
                    sysRoleMenu.setMenuId(menuId);
                    sysRoleMenu.setDefaultFieldValue();
                    sysRoleMenuList.add(sysRoleMenu);
                }
                result = this.saveBatch(sysRoleMenuList);
            }
        }

        // 刷新权限
        if (result) {
            sysPermissionService.refreshPermRolesRules();
        }
        return result;
    }

}