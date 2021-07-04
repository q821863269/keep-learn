package cn.goduck.kl.admin.mapper;

import cn.goduck.kl.admin.dto.SysUserDTO;
import cn.goduck.kl.admin.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * Desc: 
 * Author: Kon
 * Date: 2021/6/24 15:39
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUserDTO getByUsername(String username);

}