package cn.goduck.kl.admin.controller;

import cn.goduck.kl.admin.api.UserFeignClient;
import cn.goduck.kl.admin.dto.UserDTO;
import cn.goduck.kl.admin.entity.SysUser;
import cn.goduck.kl.admin.query.SysUserQuery;
import cn.goduck.kl.admin.service.SysPermissionService;
import cn.goduck.kl.admin.service.SysUserRoleService;
import cn.goduck.kl.admin.service.SysUserService;
import cn.goduck.kl.admin.vo.UserVO;
import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.core.constant.StrConstant;
import cn.goduck.kl.common.core.constant.enums.ResultCode;
import cn.goduck.kl.common.core.util.JwtUtil;
import cn.goduck.kl.common.web.exception.BizException;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
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
    private final SysUserRoleService sysUserRoleService;

    @ApiOperation(value = "列表分页")
    @GetMapping
    public R<IPage<SysUser>> page(SysUserQuery sysUserQuery) {
        return R.ok(sysUserService.page(sysUserQuery));
    }

    @ApiOperation(value = "用户详情")
    @GetMapping("/{id}")
    public R<SysUser> detail(@PathVariable @ApiParam("id") Long id) {
        SysUser sysUser = sysUserService.getById(id);
        // 查询用户角色Id集合
        if (ObjectUtil.isNotNull(sysUser)) {
            List<Long> roleIds = sysUserRoleService.roleIdList(sysUser.getId());
            sysUser.setRoleIds(roleIds);
        }
        return R.ok(sysUser);
    }

    @ApiOperation(value = "新增用户")
    @PostMapping
    public R<Object> add(@RequestBody SysUser sysUser) {
        // 校验用户名是否存在
        boolean exist = sysUserService.checkUsername(sysUser.getUsername());
        if (exist) {
            throw new BizException(ResultCode.USERNAME_EXISTS);
        }
        // 保存用户
        boolean status = sysUserService.saveUser(sysUser);
        return R.judge(status);
    }

    @ApiOperation(value = "修改用户")
    @PutMapping(value = "/{id}")
    public R<Object> update(@PathVariable @ApiParam("id") Long id,
                            @RequestBody SysUser sysUser) {
        sysUser.setId(id);
        boolean status = sysUserService.updateUser(sysUser);
        return R.judge(status);
    }

    @ApiOperation(value = "选择性更新")
    @PatchMapping(value = "/{id}")
    public R<Object> patch(@PathVariable @ApiParam("id") Long id,
                           @RequestBody SysUser sysUser) {
        sysUser.setId(id);
        boolean status = sysUserService.patchUser(sysUser);
        return R.judge(status);
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{ids}")
    public R<Object> delete(@PathVariable("ids") @ApiParam("id集合,以,拼接字符串") String ids) {
        boolean status = sysUserService.removeByIds(Arrays.asList(ids.split(StrConstant.COMMA)));
        return R.judge(status);
    }

    @ApiOperation(value = "导入用户")
    @PostMapping("/excelImport")
    public R<Object> excelImport(@RequestParam("file") MultipartFile file) {
        if (ObjectUtil.isNull(file)) {
            return R.failed(ResultCode.EXCEL_IS_EMPTY);
        }
        String result = sysUserService.excelImport(file);
        return StrUtil.isBlank(result) ? R.ok() : R.failed(result);
    }

    @ApiOperation(value = "导出用户")
    @GetMapping("/excelExport")
    public void excelExport(HttpServletResponse response, SysUserQuery sysUserQuery) {
        sysUserService.excelExport(response, sysUserQuery);
    }

    @ApiOperation(value = "根据用户名获取用户信息")
    @GetMapping("/username/{username}")
    @Override
    public R<UserDTO> getUserByUsername(@PathVariable @ApiParam("用户名") String username) {
        UserDTO userDTO = sysUserService.getByUsername(username);
        return R.ok(userDTO);
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