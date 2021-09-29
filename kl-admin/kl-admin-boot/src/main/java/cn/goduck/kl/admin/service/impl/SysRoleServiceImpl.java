package cn.goduck.kl.admin.service.impl;

import cn.goduck.kl.admin.entity.SysRole;
import cn.goduck.kl.admin.mapper.SysRoleMapper;
import cn.goduck.kl.admin.query.SysRoleQuery;
import cn.goduck.kl.admin.service.SysPermissionService;
import cn.goduck.kl.admin.service.SysRoleService;
import cn.goduck.kl.common.core.base.BaseEntity;
import cn.goduck.kl.common.core.constant.GlobalConstant;
import cn.goduck.kl.common.core.constant.StrConstant;
import cn.goduck.kl.common.core.util.JwtUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
@Service
@SuppressWarnings("unchecked")
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysPermissionService sysPermissionService;

    @Override
    public IPage<SysRole> page(SysRoleQuery sysRoleQuery) {
        List<String> roleList = JwtUtil.getRoles();
        boolean isRoot = roleList.contains(GlobalConstant.ROOT_ROLE_CODE);
        String name = sysRoleQuery.getName();
        LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<SysRole>()
                .like(StrUtil.isNotBlank(name), SysRole::getName, name)
                .ne(!isRoot, SysRole::getCode, GlobalConstant.ROOT_ROLE_CODE)
                .orderByAsc(SysRole::getSort)
                .orderByDesc(BaseEntity::getCreateTime);
        return this.page(sysRoleQuery.page(), lambdaQueryWrapper);
    }

    @Override
    public List<SysRole> roleList() {
        List<String> roles = JwtUtil.getRoles();
        // 判断是否是超级管理员
        boolean isRoot = roles.contains(GlobalConstant.ROOT_ROLE_CODE);
        LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getStatus, GlobalConstant.VALID)
                .ne(!isRoot, SysRole::getCode, GlobalConstant.ROOT_ROLE_CODE)
                .orderByAsc(SysRole::getSort);
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public boolean checkNameOrCode(SysRole sysRole) {
        int count = this.count(new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getCode, sysRole.getName())
                .or()
                .eq(SysRole::getName, sysRole.getCode()));
        return count > 0;
    }

    @Override
    public boolean saveRole(SysRole sysRole) {
        boolean result = this.save(sysRole);
        if (result) {
            sysPermissionService.refreshPermRolesRules();
        }
        return result;
    }

    @Override
    public boolean updateRole(SysRole sysRole) {
        boolean result = this.updateById(sysRole);
        if (result) {
            sysPermissionService.refreshPermRolesRules();
        }
        return result;
    }

    @Override
    public boolean patchUser(SysRole sysRole) {
        Boolean status = sysRole.getStatus();
        if (ObjectUtil.isNull(status)) {
            return false;
        }
        // 更新状态
        LambdaUpdateWrapper<SysRole> updateWrapper = new LambdaUpdateWrapper<SysRole>()
                .eq(SysRole::getId, sysRole.getId())
                .set(SysRole::getStatus, status);
        boolean result = this.update(updateWrapper);
        if (result) {
            sysPermissionService.refreshPermRolesRules();
        }
        return result;
    }

    @Override
    public boolean deleteByIds(String ids) {
        List<String> idList = Arrays.asList(ids.split(StrConstant.COMMA));
        boolean result = this.removeByIds(idList);
        if (result) {
            sysPermissionService.refreshPermRolesRules();
        }
        return result;
    }

}