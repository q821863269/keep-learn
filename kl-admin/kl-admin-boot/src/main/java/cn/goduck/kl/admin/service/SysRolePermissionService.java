package cn.goduck.kl.admin.service;

import cn.goduck.kl.admin.dto.RolePermissionDTO;
import cn.goduck.kl.admin.entity.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-07-04 22:05
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {

    List<Long> permissionIdList(Long roleId, Long menuId);

    boolean updateRolePermission(RolePermissionDTO rolePermissionDTO);

}