package cn.goduck.kl.admin.controller;

import cn.goduck.kl.admin.entity.SysDept;
import cn.goduck.kl.admin.query.SysDeptQuery;
import cn.goduck.kl.admin.service.SysDeptService;
import cn.goduck.kl.admin.vo.DeptVO;
import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.core.util.TreeUtil;
import cn.goduck.kl.common.core.vo.TreeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/30 9:28
 */
@Api(tags = "部门接口")
@RestController
@RequestMapping("/depts")
@AllArgsConstructor
public class DeptController {

    private final SysDeptService sysDeptService;

    @ApiOperation(value = "部门下拉（Select）层级列表")
    @GetMapping("/select")
    public R<List<TreeVO>> getSelectList(@ApiParam(value = "是否添加顶级") @RequestParam(required = false) Boolean addTop) {
        List<TreeVO> deptSelectList = sysDeptService.selectList();
        if (Boolean.TRUE.equals(addTop)) {
            deptSelectList = TreeUtil.addTop(deptSelectList);
        }
        return R.ok(deptSelectList);
    }

    @ApiOperation(value = "部门表格（Table）层级列表")
    @GetMapping("/table")
    public R<List<DeptVO>> getTableList(SysDeptQuery sysDeptQuery) {
        List<DeptVO> deptTableList = sysDeptService.tableList(sysDeptQuery);
        return R.ok(deptTableList);
    }

    @ApiOperation(value = "部门详情")
    @GetMapping("/{id}")
    public R<SysDept> detail(@PathVariable @ApiParam("id") Long id) {
        return R.ok(sysDeptService.getById(id));
    }

    @ApiOperation(value = "新增部门")
    @PostMapping
    public R<Object> add(@RequestBody SysDept sysDept) {
        return R.judge(sysDeptService.saveDept(sysDept));
    }

    @ApiOperation(value = "修改部门")
    @PutMapping(value = "/{id}")
    public R<Object> update(@PathVariable @ApiParam("id") Long id,
                            @RequestBody SysDept sysDept) {
        return R.judge(sysDeptService.saveDept(sysDept));
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{ids}")
    public R<Object> delete(@PathVariable("ids") @ApiParam("id集合,以,拼接字符串") String ids) {
        boolean status = sysDeptService.deleteByIds(ids);
        return R.judge(status);
    }

}
