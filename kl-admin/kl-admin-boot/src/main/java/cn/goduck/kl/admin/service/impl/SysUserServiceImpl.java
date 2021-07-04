package cn.goduck.kl.admin.service.impl;

import cn.goduck.kl.admin.dto.SysUserDTO;
import cn.goduck.kl.admin.entity.SysUser;
import cn.goduck.kl.admin.mapper.SysUserMapper;
import cn.goduck.kl.admin.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/24 15:39
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUserDTO getByUsername(String username) {
        return this.baseMapper.getByUsername(username);
    }

}