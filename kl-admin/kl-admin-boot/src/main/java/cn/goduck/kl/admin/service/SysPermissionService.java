package cn.goduck.kl.admin.service;

import cn.goduck.kl.admin.entity.SysPermission;
import cn.goduck.kl.admin.query.SysPermissionQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
public interface SysPermissionService extends IService<SysPermission> {

    List<String> listBtnPermByRoles(List<String> roles);

    IPage<SysPermission> page(Page<SysPermission> page, SysPermissionQuery sysPermissionQuery);

    boolean refreshPermRolesRules();

}