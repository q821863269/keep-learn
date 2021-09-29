package cn.goduck.kl.admin.controller;

import cn.goduck.kl.admin.entity.SysMenu;
import cn.goduck.kl.admin.query.SysMenuQuery;
import cn.goduck.kl.admin.service.SysMenuService;
import cn.goduck.kl.admin.vo.MenuVO;
import cn.goduck.kl.admin.vo.RouteVO;
import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.core.constant.StrConstant;
import cn.goduck.kl.common.core.util.TreeUtil;
import cn.goduck.kl.common.core.vo.TreeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/8/27 15:04
 */
@Api(tags = "菜单接口")
@RestController
@RequestMapping("/menus")
@AllArgsConstructor
public class MenuController {

    private final SysMenuService sysMenuService;

    @ApiOperation(value = "菜单路由（Route）层级列表")
    @GetMapping("/route")
    public R<List<RouteVO>> getMenuRouteList() {
        List<RouteVO> routeList = sysMenuService.routeList();
        return R.ok(routeList);
    }

    @ApiOperation(value = "菜单下拉（Select）层级列表")
    @GetMapping("/select")
    public R<List<TreeVO>> getMenuSelectList(@ApiParam(value = "是否添加顶级") @RequestParam(required = false) Boolean addTop) {
        List<TreeVO> menuSelectList = sysMenuService.selectList();
        if (Boolean.TRUE.equals(addTop)) {
            menuSelectList = TreeUtil.addTop(menuSelectList);
        }
        return R.ok(menuSelectList);
    }

    @ApiOperation(value = "菜单表格（Table）层级列表")
    @GetMapping("/table")
    public R<List<MenuVO>> getMenuTableList(SysMenuQuery sysMenuQuery) {
        List<MenuVO> sysMenuVOList = sysMenuService.tableList(sysMenuQuery);
        return R.ok(sysMenuVOList);
    }

    @ApiOperation(value = "菜单详情")
    @GetMapping("/{id}")
    public R<SysMenu> detail(@PathVariable @ApiParam("id") Long id) {
        return R.ok(sysMenuService.getById(id));
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping
    @CacheEvict(value = "admin", key = "'routeList'")
    public R<Object> add(@RequestBody SysMenu sysMenu) {
        return R.judge(sysMenuService.save(sysMenu));
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping(value = "/{id}")
    @CacheEvict(value = "admin", key = "'routeList'")
    public R<Object> update(@PathVariable @ApiParam("id") Long id, @RequestBody SysMenu sysMenu) {
        return R.judge(sysMenuService.updateById(sysMenu));
    }

    @ApiOperation(value = "选择性修改菜单")
    @PatchMapping(value = "/{id}")
    @CacheEvict(value = "admin", key = "'routeList'")
    public R<Object> patch(@PathVariable Integer id, @RequestBody SysMenu sysMenu) {
        return R.judge(sysMenuService.patchMenu(sysMenu));
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/{ids}")
    @CacheEvict(value = "admin", key = "'routeList'")
    public R<Object> delete(@PathVariable("ids") @ApiParam("id集合,以,拼接字符串") String ids) {
        return R.judge(sysMenuService.removeByIds(Arrays.asList(ids.split(StrConstant.COMMA))));
    }

}
