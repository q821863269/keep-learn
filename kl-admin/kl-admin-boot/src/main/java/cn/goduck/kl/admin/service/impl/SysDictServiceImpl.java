package cn.goduck.kl.admin.service.impl;

import cn.goduck.kl.admin.entity.SysDict;
import cn.goduck.kl.admin.entity.SysDictItem;
import cn.goduck.kl.admin.mapper.SysDictMapper;
import cn.goduck.kl.admin.query.SysDictQuery;
import cn.goduck.kl.admin.service.SysDictItemService;
import cn.goduck.kl.admin.service.SysDictService;
import cn.goduck.kl.common.core.base.BaseEntity;
import cn.goduck.kl.common.core.constant.StrConstant;
import cn.goduck.kl.common.core.constant.enums.ResultCode;
import cn.goduck.kl.common.web.exception.BizException;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/29 10:27
 */
@Service
@SuppressWarnings("unchecked")
@AllArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    private final SysDictItemService sysDictItemService;

    @Override
    public IPage<SysDict> page(SysDictQuery sysDictQuery) {
        String name = sysDictQuery.getName();
        LambdaQueryWrapper<SysDict> lambdaQueryWrapper = new LambdaQueryWrapper<SysDict>()
                .like(StrUtil.isNotBlank(name), SysDict::getName, name)
                .orderByDesc(BaseEntity::getCreateTime);
        return this.page(sysDictQuery.page(), lambdaQueryWrapper);
    }

    @Override
    public List<SysDict> dictList() {
        return this.list(new LambdaQueryWrapper<SysDict>().orderByDesc(BaseEntity::getCreateTime));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateDict(SysDict sysDict) {
        SysDict dbDict = this.getById(sysDict.getId());
        boolean result = this.updateById(sysDict);
        if (result) {
            // 字典code更新，同步更新字典项code
            if (!StrUtil.equals(dbDict.getCode(), sysDict.getCode())) {
                sysDictItemService.update(new LambdaUpdateWrapper<SysDictItem>()
                        .eq(SysDictItem::getDictCode, dbDict.getCode())
                        .set(SysDictItem::getDictCode, sysDict.getCode()));
            }
        }
        return result;
    }

    @Override
    public boolean patchDict(SysDict sysDict) {
        Boolean status = sysDict.getStatus();
        LambdaUpdateWrapper<SysDict> lambdaUpdateWrapper = new LambdaUpdateWrapper<SysDict>()
                .eq(BaseEntity::getId, sysDict.getId())
                .set(ObjectUtil.isNotNull(status), SysDict::getStatus, status);
        return this.update(lambdaUpdateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByIds(String ids) {
        List<String> idList = Arrays.asList(ids.split(StrConstant.COMMA));
        List<SysDict> sysDictList = this.listByIds(idList);
        List<String> codeList = sysDictList.stream().map(SysDict::getCode).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(codeList)) {
            int count = sysDictItemService.count(new LambdaQueryWrapper<SysDictItem>()
                    .in(SysDictItem::getDictCode, codeList));
            if (count > 0) throw new BizException(ResultCode.FIRST_DELETE_ASSOCIATE_DICT_ITEM);
        }
        return this.removeByIds(idList);
    }

}
