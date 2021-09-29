package cn.goduck.kl.admin.controller;

import cn.goduck.kl.admin.dto.RolePermissionDTO;
import cn.goduck.kl.admin.entity.SysRole;
import cn.goduck.kl.admin.query.SysRoleQuery;
import cn.goduck.kl.admin.service.SysRoleMenuService;
import cn.goduck.kl.admin.service.SysRolePermissionService;
import cn.goduck.kl.admin.service.SysRoleService;
import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.core.constant.enums.ResultCode;
import cn.goduck.kl.common.web.exception.BizException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/30 11:39
 */
@Api(tags = "角色接口")
@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {

    private final SysRoleService sysRoleService;
    private final SysRoleMenuService sysRoleMenuService;
    private final SysRolePermissionService sysRolePermissionService;

    @ApiOperation(value = "列表分页")
    @GetMapping
    public R<IPage<SysRole>> page(SysRoleQuery sysRoleQuery) {
        return R.ok(sysRoleService.page(sysRoleQuery));
    }

    @ApiOperation(value = "角色列表")
    @GetMapping("/list")
    public R<List<SysRole>> list() {
        return R.ok(sysRoleService.roleList());
    }

    @ApiOperation(value = "新增角色")
    @PostMapping
    public R<Object> add(@RequestBody SysRole sysRole) {
        // 校验角色名称或角色编码是否存在
        boolean exist = sysRoleService.checkNameOrCode(sysRole);
        if (exist) {
            throw new BizException(ResultCode.ROLE_NAME_OR_CODE_EXISTS);
        }
        // 保存角色
        return R.judge(sysRoleService.saveRole(sysRole));
    }

    @ApiOperation(value = "修改角色")
    @PutMapping(value = "/{id}")
    public R<Object> update(@PathVariable @ApiParam("id") Long id, @RequestBody SysRole sysRole) {
        // 校验角色名称或角色编码是否存在
        boolean exist = sysRoleService.checkNameOrCode(sysRole);
        if (exist) {
            throw new BizException(ResultCode.ROLE_NAME_OR_CODE_EXISTS);
        }
        return R.judge(sysRoleService.updateRole(sysRole));
    }

    @ApiOperation(value = "选择性更新")
    @PatchMapping(value = "/{id}")
    public R<Object> patch(@PathVariable @ApiParam("id") Long id, @RequestBody SysRole sysRole) {
        return R.judge(sysRoleService.patchUser(sysRole));
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/{ids}")
    public R<Object> delete(@PathVariable("ids") @ApiParam("id集合,以,拼接字符串") String ids) {
        return R.judge(sysRoleService.deleteByIds(ids));
    }

    @ApiOperation(value = "获取角色拥有的菜单ID集合")
    @GetMapping("/{id}/menus")
    public R<List<Long>> listRoleMenu(@PathVariable("id") @ApiParam("角色id") Long roleId) {
        List<Long> menuIdList = sysRoleMenuService.menuIdList(roleId);
        return R.ok(menuIdList);
    }

    @ApiOperation(value = "获取角色拥有的权限ID集合")
    @GetMapping("/{id}/permissions")
    public R<List<Long>> listRolePermission(@PathVariable("id") @ApiParam("角色id") Long roleId,
                                            @ApiParam("菜单id") Long menuId) {
        List<Long> permissionIdList = sysRolePermissionService.permissionIdList(roleId, menuId);
        return R.ok(permissionIdList);
    }

    @ApiOperation(value = "修改角色菜单")
    @PutMapping(value = "/{id}/menus")
    public R<Object> updateRoleMenu(@PathVariable("id") @ApiParam("角色id") Long roleId,
                                 @RequestBody SysRole role) {
        List<Long> menuIdList = role.getMenuIds();
        return R.judge(sysRoleMenuService.updateRoleMenu(roleId, menuIdList));
    }

    @ApiOperation(value = "修改角色权限")
    @PutMapping(value = "/{id}/permissions")
    public R<Object> updateRolePermission(@PathVariable("id") Long roleId,
                                          @RequestBody RolePermissionDTO rolePermissionDTO) {
        rolePermissionDTO.setRoleId(roleId);
        return R.judge(sysRolePermissionService.updateRolePermission(rolePermissionDTO));
    }

}
