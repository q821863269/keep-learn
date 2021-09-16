package cn.goduck.kl.admin.mapper;

import cn.goduck.kl.admin.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * Desc: 
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> menuList();

}