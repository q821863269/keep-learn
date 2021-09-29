package cn.goduck.kl.admin.service;

import cn.goduck.kl.admin.entity.SysDict;
import cn.goduck.kl.admin.query.SysDictQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/29 10:27
 */
public interface SysDictService extends IService<SysDict> {

    IPage<SysDict> page(SysDictQuery sysDictQuery);

    List<SysDict> dictList();

    boolean updateDict(SysDict sysDict);

    boolean patchDict(SysDict sysDict);

    boolean deleteByIds(String ids);

}
