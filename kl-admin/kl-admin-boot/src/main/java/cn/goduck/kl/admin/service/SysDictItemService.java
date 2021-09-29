package cn.goduck.kl.admin.service;

import cn.goduck.kl.admin.entity.SysDictItem;
import cn.goduck.kl.admin.query.SysDictItemQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/29 10:28
 */
public interface SysDictItemService extends IService<SysDictItem> {

    IPage<SysDictItem> page(SysDictItemQuery sysDictItemQuery);

    List<SysDictItem> dictItemList(SysDictItemQuery sysDictItemQuery);

    boolean patchDictItem(SysDictItem sysDictItem);

}
