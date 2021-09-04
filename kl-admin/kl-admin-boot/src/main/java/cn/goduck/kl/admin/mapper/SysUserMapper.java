package cn.goduck.kl.admin.mapper;

import cn.goduck.kl.admin.dto.UserDTO;
import cn.goduck.kl.admin.entity.SysUser;
import cn.goduck.kl.admin.query.UserQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * Desc: 
 * Author: Kon
 * Date: 2021/6/24 15:39
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    UserDTO getByUsername(String username);

    IPage<SysUser> page(Page<SysUser> page, @Param("query") UserQuery userQuery);

}