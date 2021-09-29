package cn.goduck.kl.admin.controller;

import cn.goduck.kl.admin.api.OAuthClientFeignClient;
import cn.goduck.kl.admin.entity.SysOauthClient;
import cn.goduck.kl.admin.query.SysOauthClientQuery;
import cn.goduck.kl.admin.service.SysOauthClientService;
import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.core.constant.StrConstant;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/24 20:58
 */
@Api(tags = "客户端接口")
@RestController
@RequestMapping("/oauthClients")
@AllArgsConstructor
public class OauthClientController implements OAuthClientFeignClient {

    private final SysOauthClientService sysOauthClientService;

    @ApiOperation(value = "列表分页")
    @GetMapping
    public R<IPage<SysOauthClient>> page(SysOauthClientQuery sysOauthClientQuery) {
        IPage<SysOauthClient> page = sysOauthClientService.page(sysOauthClientQuery);
        return R.ok(page);
    }

    @ApiOperation(value = "客户端详情")
    @GetMapping("/{id}")
    public R<SysOauthClient> detail(@PathVariable @ApiParam("id") String id) {
        return R.ok(sysOauthClientService.getById(id));
    }

    @ApiOperation(value = "新增客户端")
    @PostMapping
    public R<Object> add(@RequestBody SysOauthClient client) {
        return R.judge(sysOauthClientService.save(client));
    }

    @ApiOperation(value = "修改客户端")
    @PutMapping(value = "/{id}")
    public R<Object> update(@PathVariable @ApiParam("id") Long id, @RequestBody SysOauthClient client) {
        return R.judge(sysOauthClientService.updateById(client));
    }

    @ApiOperation(value = "删除客户端")
    @DeleteMapping("/{ids}")
    public R<Object> delete(@PathVariable("ids") @ApiParam("id集合,以,拼接字符串") String ids) {
        return R.judge(sysOauthClientService.removeByIds(Arrays.asList(ids.split(StrConstant.COMMA))));
    }

    @ApiOperation(value = "根据客户端id获取客户端信息")
    @GetMapping("/clientId/{clientId}")
    @Override
    public R<SysOauthClient> getOAuthClientById(@PathVariable @ApiParam("客户端id") String clientId) {
        return R.ok(sysOauthClientService.getOAuthClientById(clientId));
    }

}