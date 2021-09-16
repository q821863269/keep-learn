package cn.goduck.kl.admin.mapper;

import cn.goduck.kl.admin.entity.SysPermission;
import cn.goduck.kl.admin.query.SysPermissionQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<String> listBtnPermByRoles(@Param("roles") List<String> roles);

    IPage<SysPermission> page(Page<SysPermission> page, @Param("sysPermissionQuery") SysPermissionQuery sysPermissionQuery);

    List<SysPermission> permRolesList();

}