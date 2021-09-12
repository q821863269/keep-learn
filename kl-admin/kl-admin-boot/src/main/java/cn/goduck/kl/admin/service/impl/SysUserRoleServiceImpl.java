package cn.goduck.kl.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.goduck.kl.admin.entity.SysUserRole;
import cn.goduck.kl.admin.mapper.SysUserRoleMapper;
import cn.goduck.kl.admin.service.SysUserRoleService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public List<Long> roleIdList(Long userId) {
        return this.list(new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, userId)
                .select(SysUserRole::getRoleId)
        ).stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
    }

}