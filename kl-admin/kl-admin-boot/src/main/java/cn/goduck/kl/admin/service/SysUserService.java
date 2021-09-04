package cn.goduck.kl.admin.service;

import cn.goduck.kl.admin.dto.UserDTO;
import cn.goduck.kl.admin.entity.SysUser;
import cn.goduck.kl.admin.query.UserQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/24 15:39
 */
public interface SysUserService extends IService<SysUser> {

    UserDTO getByUsername(String username);

    IPage<SysUser> page(UserQuery userQuery);

}