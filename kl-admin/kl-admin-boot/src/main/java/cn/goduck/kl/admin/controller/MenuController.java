package cn.goduck.kl.admin.controller;

import cn.goduck.kl.admin.service.SysMenuService;
import cn.goduck.kl.admin.vo.RouteVO;
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
 * Date: 2021/8/27 15:04
 */
@Api(tags = "菜单接口")
@RestController
@RequestMapping("/menu")
@AllArgsConstructor
public class MenuController {

    private final SysMenuService sysMenuService;

    @ApiOperation(value = "菜单路由（Route）层级列表")
    @GetMapping("/route")
    public R<List<RouteVO>> getMenuRouteList() {
        List<RouteVO> routeList = sysMenuService.routeList();
        return R.ok(routeList);
    }

}
