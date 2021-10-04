package cn.goduck.kl.admin.controller;

import cn.goduck.kl.admin.entity.SysDictItem;
import cn.goduck.kl.admin.query.SysDictItemQuery;
import cn.goduck.kl.admin.service.SysDictItemService;
import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.core.constant.StrConstant;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/21 17:12
 */
@Api(tags = "字典项接口")
@RestController
@RequestMapping("/dictItems")
@AllArgsConstructor
public class DictItemController {

    private final SysDictItemService sysDictItemService;

    @ApiOperation(value = "列表分页")
    @GetMapping
    public R<IPage<SysDictItem>> page(SysDictItemQuery sysDictItemQuery) {
        IPage<SysDictItem> page = sysDictItemService.page(sysDictItemQuery);
        return R.ok(page);
    }

    @ApiOperation(value = "字典项列表")
    @GetMapping("/list")
    public R<List<SysDictItem>> list(SysDictItemQuery sysDictItemQuery) {
        List<SysDictItem> list = sysDictItemService.dictItemList(sysDictItemQuery);
        return R.ok(list);
    }

    @ApiOperation(value = "字典项详情")
    @GetMapping("/{id}")
    public R<SysDictItem> detail(@PathVariable Long id) {
        return R.ok(sysDictItemService.getById(id));
    }

    @ApiOperation(value = "新增字典项")
    @PostMapping
    public R<Object> add(@RequestBody SysDictItem sysDictItem) {
        return R.judge(sysDictItemService.save(sysDictItem));
    }

    @ApiOperation(value = "修改字典项")
    @PutMapping(value = "/{id}")
    public R<Object> update(@PathVariable Long id, @RequestBody SysDictItem sysDictItem) {
        return R.judge(sysDictItemService.updateById(sysDictItem));
    }

    @ApiOperation(value = "选择性更新字典数据")
    @PatchMapping(value = "/{id}")
    public R<Object> patch(@PathVariable Long id, @RequestBody SysDictItem sysDictItem) {
        return R.judge(sysDictItemService.patchDictItem(sysDictItem));
    }

    @ApiOperation(value = "删除字典数据")
    @DeleteMapping("/{ids}")
    public R<Object> delete(@PathVariable String ids) {
        return R.judge(sysDictItemService.removeByIds(Arrays.asList(ids.split(StrConstant.COMMA))));
    }

}
