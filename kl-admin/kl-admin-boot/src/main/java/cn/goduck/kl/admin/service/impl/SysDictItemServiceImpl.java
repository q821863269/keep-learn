package cn.goduck.kl.admin.service.impl;

import cn.goduck.kl.admin.entity.SysDictItem;
import cn.goduck.kl.admin.mapper.SysDictItemMapper;
import cn.goduck.kl.admin.query.SysDictItemQuery;
import cn.goduck.kl.admin.service.SysDictItemService;
import cn.goduck.kl.common.core.base.BaseEntity;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/29 10:28
 */
@Service
@SuppressWarnings("unchecked")
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {

    @Override
    public IPage<SysDictItem> page(SysDictItemQuery sysDictItemQuery) {
        String name = sysDictItemQuery.getName();
        String dictCode = sysDictItemQuery.getDictCode();
        LambdaQueryWrapper<SysDictItem> lambdaQueryWrapper = new LambdaQueryWrapper<SysDictItem>()
                .like(StrUtil.isNotBlank(name), SysDictItem::getName, name)
                .eq(StrUtil.isNotBlank(dictCode), SysDictItem::getDictCode, dictCode);
        return this.page(sysDictItemQuery.page(), lambdaQueryWrapper);
    }

    @Override
    public List<SysDictItem> dictItemList(SysDictItemQuery sysDictItemQuery) {
        String name = sysDictItemQuery.getName();
        String dictCode = sysDictItemQuery.getDictCode();
        LambdaQueryWrapper<SysDictItem> lambdaQueryWrapper = new LambdaQueryWrapper<SysDictItem>()
                .like(StrUtil.isNotBlank(name), SysDictItem::getName, name)
                .eq(StrUtil.isNotBlank(dictCode), SysDictItem::getDictCode, dictCode)
                .select(SysDictItem::getName, SysDictItem::getValue)
                .orderByAsc(SysDictItem::getSort);
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public boolean patchDictItem(SysDictItem sysDictItem) {
        Boolean status = sysDictItem.getStatus();
        LambdaUpdateWrapper<SysDictItem> lambdaUpdateWrapper = new LambdaUpdateWrapper<SysDictItem>()
                .eq(BaseEntity::getId, sysDictItem.getId())
                .set(ObjectUtil.isNotNull(status), SysDictItem::getStatus, status);
        return this.update(lambdaUpdateWrapper);
    }

}
