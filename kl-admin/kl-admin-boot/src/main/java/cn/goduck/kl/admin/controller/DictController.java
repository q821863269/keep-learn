package cn.goduck.kl.admin.controller;

import cn.goduck.kl.admin.entity.SysDict;
import cn.goduck.kl.admin.query.SysDictQuery;
import cn.goduck.kl.admin.service.SysDictService;
import cn.goduck.kl.common.core.base.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/21 17:12
 */
@Api(tags = "字典接口")
@RestController
@RequestMapping("/dicts")
@AllArgsConstructor
public class DictController {

    private final SysDictService sysDictService;

    @ApiOperation(value = "列表分页")
    @GetMapping("/page")
    public R<IPage<SysDict>> page(SysDictQuery sysDictQuery) {
        IPage<SysDict> page = sysDictService.page(sysDictQuery);
        return R.ok(page);
    }

    @ApiOperation(value = "字典列表")
    @GetMapping
    public R<List<SysDict>> list() {
        List<SysDict> list = sysDictService.dictList();
        return R.ok(list);
    }

    @ApiOperation(value = "字典详情")
    @GetMapping("/{id}")
    public R<SysDict> detail(@PathVariable Long id) {
        return R.ok(sysDictService.getById(id));
    }

    @ApiOperation(value = "新增字典")
    @PostMapping
    public R<Object> add(@RequestBody SysDict sysDict) {
        return R.judge(sysDictService.save(sysDict));
    }

    @ApiOperation(value = "修改字典")
    @PutMapping(value = "/{id}")
    public R<Object> update(@PathVariable Long id, @RequestBody SysDict sysDict) {
        return R.judge(sysDictService.updateDict(sysDict));
    }

    @ApiOperation(value = "选择性更新字典")
    @PatchMapping(value = "/{id}")
    public R<Object> patch(@PathVariable Long id, @RequestBody SysDict sysDict) {
        return R.judge(sysDictService.patchDict(sysDict));
    }

    @ApiOperation(value = "删除字典")
    @DeleteMapping("/{ids}")
    public R<Object> delete(@PathVariable String ids) {
        return R.judge(sysDictService.deleteByIds(ids));
    }

}
