package cn.goduck.kl.admin.mapper;

import cn.goduck.kl.admin.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<String> listBtnPermByRoles(@Param("roles") List<String> roles);

}