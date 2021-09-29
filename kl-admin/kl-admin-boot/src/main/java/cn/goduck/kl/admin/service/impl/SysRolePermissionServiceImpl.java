package cn.goduck.kl.admin.service.impl;

import cn.goduck.kl.admin.dto.RolePermissionDTO;
import cn.goduck.kl.admin.entity.SysRolePermission;
import cn.goduck.kl.admin.mapper.SysRolePermissionMapper;
import cn.goduck.kl.admin.service.SysPermissionService;
import cn.goduck.kl.admin.service.SysRolePermissionService;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-07-04 22:05
 */
@Service
@AllArgsConstructor
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    private final SysPermissionService sysPermissionService;

    @Override
    public List<Long> permissionIdList(Long roleId, Long menuId) {
        return this.baseMapper.permissionIdList(roleId, menuId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRolePermission(RolePermissionDTO rolePermissionDTO) {
        boolean result = true;
        List<Long> permissionIdList = rolePermissionDTO.getPermissionIds();
        Long menuId = rolePermissionDTO.getMenuId();
        Long roleId = rolePermissionDTO.getRoleId();
        List<Long> dbPermissionIdList = this.baseMapper.permissionIdList(roleId, menuId);

        // 删除数据库有，permissionIdList没有的
        if (CollectionUtil.isNotEmpty(dbPermissionIdList)) {
            List<Long> removePermissionIdList = dbPermissionIdList.stream().filter(id -> !permissionIdList.contains(id)).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(removePermissionIdList)) {
                this.remove(new LambdaQueryWrapper<SysRolePermission>().eq(SysRolePermission::getRoleId, roleId)
                        .in(SysRolePermission::getPermissionId, removePermissionIdList));
            }
        }

        // 删除数据库不存在的
        if (CollectionUtil.isNotEmpty(permissionIdList)) {
            List<Long> insertPermissionIdList = permissionIdList.stream().filter(id -> !dbPermissionIdList.contains(id)).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(insertPermissionIdList)) {
                List<SysRolePermission> rolePermissionList = new ArrayList<>();
                for (Long insertPermissionId : insertPermissionIdList) {
                    SysRolePermission sysRolePermission = new SysRolePermission();
                    sysRolePermission.setRoleId(roleId);
                    sysRolePermission.setPermissionId(insertPermissionId);
                    sysRolePermission.setDefaultFieldValue();
                    rolePermissionList.add(sysRolePermission);
                }
                result = this.saveBatch(rolePermissionList);
            }
        }

        // 刷新权限
        if (result) {
            sysPermissionService.refreshPermRolesRules();
        }
        return result;
    }

}