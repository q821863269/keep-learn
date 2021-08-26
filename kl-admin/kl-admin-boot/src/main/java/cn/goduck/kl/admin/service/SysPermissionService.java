package cn.goduck.kl.admin.service;

import cn.goduck.kl.admin.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
public interface SysPermissionService extends IService<SysPermission> {

    List<String> listBtnPermByRoles(List<String> roles);

}