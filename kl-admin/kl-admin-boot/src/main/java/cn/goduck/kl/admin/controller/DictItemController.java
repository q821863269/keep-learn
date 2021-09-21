package cn.goduck.kl.admin.controller;

import cn.goduck.kl.admin.query.SysDictItemQuery;
import cn.goduck.kl.common.core.base.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/21 17:12
 */
@Api(tags = "字典接口")
@RestController
@RequestMapping("/dictItems")
@AllArgsConstructor
public class DictItemController {

    @ApiOperation(value = "列表分页")
    @GetMapping("/page")
    public R<Object> page(SysDictItemQuery sysDictItemQuery) {
        return R.ok();
    }

    @ApiOperation(value = "字典项列表")
    @GetMapping
    public R<Object> list(SysDictItemQuery sysDictItemQuery) {
        return R.ok();
    }

    @ApiOperation(value = "字典项详情")
    @GetMapping("/{id}")
    public R<Object> detail(@PathVariable Long id) {
        return R.ok();
    }

    @ApiOperation(value = "新增字典项")
    @PostMapping
    public R<Object> add() {
        return R.ok();
    }

    @ApiOperation(value = "修改字典项")
    @PutMapping(value = "/{id}")
    public R<Object> update(@PathVariable Long id) {
        return R.ok();
    }

    @ApiOperation(value = "选择性更新字典数据")
    @PatchMapping(value = "/{id}")
    public R<Object> patch(@PathVariable Long id) {
        return R.ok();
    }

    @ApiOperation(value = "删除字典数据")
    @DeleteMapping("/{ids}")
    public R<Object> delete(@PathVariable String ids) {
        return R.ok();
    }

}
