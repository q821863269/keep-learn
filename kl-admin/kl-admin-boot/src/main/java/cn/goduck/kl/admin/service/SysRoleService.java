package cn.goduck.kl.admin.service;

import cn.goduck.kl.admin.entity.SysRole;
import cn.goduck.kl.admin.entity.SysUser;
import cn.goduck.kl.admin.query.SysRoleQuery;
import cn.goduck.kl.admin.query.SysUserQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
public interface SysRoleService extends IService<SysRole> {

    IPage<SysRole> page(SysRoleQuery sysRoleQuery);

    List<SysRole> roleList();

    boolean checkNameOrCode(SysRole sysRole);

    boolean saveRole(SysRole sysRole);

    boolean updateRole(SysRole sysRole);

    boolean patchUser(SysRole sysRole);

    boolean deleteByIds(String ids);

}