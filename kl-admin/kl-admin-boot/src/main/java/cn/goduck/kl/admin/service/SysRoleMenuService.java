package cn.goduck.kl.admin.service;

import cn.goduck.kl.admin.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    List<Long> menuIdList(Long roleId);

    boolean updateRoleMenu(Long roleId, List<Long> menuIdList);

}