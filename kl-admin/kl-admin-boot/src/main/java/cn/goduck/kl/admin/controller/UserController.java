package cn.goduck.kl.admin.controller;

import cn.goduck.kl.admin.api.UserFeignClient;
import cn.goduck.kl.admin.dto.SysUserDTO;
import cn.goduck.kl.admin.entity.SysUser;
import cn.goduck.kl.admin.service.SysPermissionService;
import cn.goduck.kl.admin.service.SysUserService;
import cn.goduck.kl.admin.vo.UserVO;
import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.core.util.JwtUtil;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-07-03 15:23
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController implements UserFeignClient {

    private final SysUserService sysUserService;
    private final SysPermissionService sysPermissionService;

    @ApiOperation(value = "根据用户名获取用户信息")
    @GetMapping("/username/{username}")
    @Override
    public R<SysUserDTO> getUserByUsername(@PathVariable @ApiParam("用户名") String username) {
        SysUserDTO sysUserDTO = sysUserService.getByUsername(username);
        return R.ok(sysUserDTO);
    }

    @ApiOperation(value = "获取当前登陆的用户信息")
    @GetMapping("/me")
    public R<UserVO> getCurrentUser() {
        UserVO userVO = new UserVO();
        // 用户基本信息
        Long userId = JwtUtil.getUserId();
        SysUser user = sysUserService.getById(userId);
        BeanUtil.copyProperties(user, userVO);
        // 用户角色信息
        List<String> roles = JwtUtil.getRoles();
        userVO.setRoles(roles);
        // 用户按钮权限信息
        List<String> perms = sysPermissionService.listBtnPermByRoles(roles);
        userVO.setPerms(perms);
        return R.ok(userVO);
    }

}