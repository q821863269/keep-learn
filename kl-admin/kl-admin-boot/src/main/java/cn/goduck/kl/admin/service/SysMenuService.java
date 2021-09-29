package cn.goduck.kl.admin.service;

import cn.goduck.kl.admin.entity.SysMenu;
import cn.goduck.kl.admin.query.SysMenuQuery;
import cn.goduck.kl.admin.vo.RouteVO;
import cn.goduck.kl.admin.vo.MenuVO;
import cn.goduck.kl.common.core.vo.TreeVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
public interface SysMenuService extends IService<SysMenu> {

    List<RouteVO> routeList();

    List<TreeVO> selectList();

    List<MenuVO> tableList(SysMenuQuery sysMenuQuery);

    boolean patchMenu(SysMenu sysMenu);

}