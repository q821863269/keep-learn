package cn.goduck.kl.admin.service;

import cn.goduck.kl.admin.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/25 16:34
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> roleList();

}