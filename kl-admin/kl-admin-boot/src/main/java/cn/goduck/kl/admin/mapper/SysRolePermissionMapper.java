package cn.goduck.kl.admin.mapper;

import cn.goduck.kl.admin.entity.SysRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Desc: 
 * Author: Kon
 * Date: 2021-07-04 22:05
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

    List<Long> permissionIdList(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

}