package cn.goduck.kl.admin.mapper;

import cn.goduck.kl.admin.entity.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Desc: 
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    List<Long> menuIdList(@Param("roleId") Long roleId);

}