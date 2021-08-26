package cn.goduck.kl.admin.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.goduck.kl.admin.mapper.SysPermissionMapper;
import cn.goduck.kl.admin.entity.SysPermission;
import cn.goduck.kl.admin.service.SysPermissionService;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Override
    public List<String> listBtnPermByRoles(List<String> roles) {
        return this.baseMapper.listBtnPermByRoles(roles);
    }

}