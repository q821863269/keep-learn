package cn.goduck.kl.admin.service.impl;

import cn.goduck.kl.admin.entity.SysRole;
import cn.goduck.kl.admin.mapper.SysRoleMapper;
import cn.goduck.kl.admin.service.SysRoleService;
import cn.goduck.kl.common.core.constant.GlobalConstant;
import cn.goduck.kl.common.core.util.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
@Service
@SuppressWarnings("unchecked")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> roleList() {
        List<String> roles = JwtUtil.getRoles();
        // 判断是否是超级管理员
        boolean isRoot = roles.contains(GlobalConstant.ROOT_ROLE_CODE);
        LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysRole::getStatus, GlobalConstant.VALID);
        lambdaQueryWrapper.ne(!isRoot, SysRole::getCode, GlobalConstant.ROOT_ROLE_CODE);
        lambdaQueryWrapper.orderByAsc(SysRole::getSort);
        return this.list(lambdaQueryWrapper);
    }

}