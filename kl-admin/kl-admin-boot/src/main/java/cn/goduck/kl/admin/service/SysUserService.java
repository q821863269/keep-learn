package cn.goduck.kl.admin.service;

import cn.goduck.kl.admin.dto.SysUserDTO;
import cn.goduck.kl.admin.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/24 15:39
 */
public interface SysUserService extends IService<SysUser> {

    SysUserDTO getByUsername(String username);

}