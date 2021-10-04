package cn.goduck.kl.admin.controller;

import cn.goduck.kl.admin.entity.SysPermission;
import cn.goduck.kl.admin.query.SysPermissionQuery;
import cn.goduck.kl.admin.service.SysPermissionService;
import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.core.constant.StrConstant;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/9/16 16:22
 */
@Api(tags = "权限接口")
@RestController
@RequestMapping("/permissions")
@AllArgsConstructor
public class PermissionController {

    private final SysPermissionService sysPermissionService;

    @ApiOperation(value = "列表分页")
    @GetMapping
    public R<IPage<SysPermission>> page(SysPermissionQuery sysPermissionQuery) {
        IPage<SysPermission> page = sysPermissionService.page(sysPermissionQuery.page(), sysPermissionQuery);
        return R.ok(page);
    }

    @ApiOperation(value = "权限列表")
    @GetMapping("/list")
    public R<List<SysPermission>> list(@ApiParam("菜单id") @Param("menuId") Long menuId) {
        List<SysPermission> list = sysPermissionService.permissionList(menuId);
        return R.ok(list);
    }

    @ApiOperation(value = "权限详情")
    @GetMapping("/{id}")
    public R<SysPermission> detail(@PathVariable @ApiParam("id") Long id) {
        return R.ok(sysPermissionService.getById(id));
    }

    @ApiOperation(value = "新增权限")
    @PostMapping
    public R<Object> add(@RequestBody SysPermission sysPermission) {
        boolean result = sysPermissionService.save(sysPermission);
        if (result) {
            sysPermissionService.refreshPermRolesRules();
        }
        return R.judge(result);
    }

    @ApiOperation(value = "修改权限")
    @PutMapping(value = "/{id}")
    public R<Object> update(@PathVariable @ApiParam("id") Long id, @RequestBody SysPermission sysPermission) {
        boolean result = sysPermissionService.updateById(sysPermission);
        if (result) {
            sysPermissionService.refreshPermRolesRules();
        }
        return R.judge(result);
    }

    @ApiOperation(value = "删除权限")
    @DeleteMapping("/{ids}")
    public R<Object> delete(@PathVariable("ids") @ApiParam("id集合,以,拼接字符串") String ids) {
        return R.judge(sysPermissionService.removeByIds(Arrays.asList(ids.split(StrConstant.COMMA))));
    }

}
