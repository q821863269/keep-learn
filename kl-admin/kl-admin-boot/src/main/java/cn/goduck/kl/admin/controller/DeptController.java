package cn.goduck.kl.admin.controller;

import cn.goduck.kl.admin.service.SysDeptService;
import cn.goduck.kl.admin.vo.DeptVO;
import cn.goduck.kl.admin.vo.TreeVO;
import cn.goduck.kl.common.core.base.R;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/30 9:28
 */
@Api(tags = "部门接口")
@RestController
@RequestMapping("/dept")
@AllArgsConstructor
public class DeptController {

    private final SysDeptService sysDeptService;

    @ApiOperation(value = "部门表格（Table）层级列表")
    @GetMapping("/table")
    public R<List<DeptVO>> getTableList(@ApiParam("部门状态") Integer status,
                                        @ApiParam("部门名称") String name) {
        List<DeptVO> deptTableList = sysDeptService.listTable(status, name);
        return R.ok(deptTableList);
    }

    @ApiOperation(value = "部门下拉（Select）层级列表")
    @GetMapping("/select")
    public R<List<TreeVO>> getSelectList() {
        List<TreeVO> deptSelectList = sysDeptService.listSelect();
        return R.ok(deptSelectList);
    }

}
