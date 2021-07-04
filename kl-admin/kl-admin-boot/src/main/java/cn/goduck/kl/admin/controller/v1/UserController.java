package cn.goduck.kl.admin.controller.v1;

import cn.goduck.kl.admin.api.UserFeignClient;
import cn.goduck.kl.admin.dto.SysUserDTO;
import cn.goduck.kl.admin.service.SysUserService;
import cn.goduck.kl.common.core.base.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-07-03 15:23
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController implements UserFeignClient {

    private final SysUserService sysUserService;

    @ApiOperation(value = "根据用户名获取用户信息")
    @GetMapping("/username/{username}")
    @Override
    public R<SysUserDTO> getUserByUsername(@PathVariable @ApiParam("用户名") String username) {
        SysUserDTO sysUserDTO = sysUserService.getByUsername(username);
        return R.ok(sysUserDTO);
    }

}