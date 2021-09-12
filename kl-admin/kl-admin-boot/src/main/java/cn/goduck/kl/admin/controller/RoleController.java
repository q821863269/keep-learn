package cn.goduck.kl.admin.controller;

import cn.goduck.kl.admin.entity.SysRole;
import cn.goduck.kl.admin.service.SysRoleService;
import cn.goduck.kl.common.core.base.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "角色列表")
    @GetMapping("/list")
    public R<List<SysRole>> list() {
        return R.ok(sysRoleService.roleList());
    }

}
