package cn.goduck.kl.admin.controller.v1;

import cn.goduck.kl.admin.api.OAuthClientFeignClient;
import cn.goduck.kl.admin.entity.SysOauthClient;
import cn.goduck.kl.admin.service.SysOauthClientService;
import cn.goduck.kl.common.core.base.BasePageQuery;
import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.core.constant.StrConstant;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
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
@RequestMapping("/api/v1/oauth-clients")
@AllArgsConstructor
public class OauthClientController implements OAuthClientFeignClient {

    private final SysOauthClientService sysOauthClientService;

    @ApiOperation(value = "列表分页")
    @GetMapping
    public R<IPage<SysOauthClient>> list(@ApiParam("条件分页请求入参") BasePageQuery query, @ApiParam("客户端id") String clientId) {
        IPage<SysOauthClient> page = sysOauthClientService.page(
                new Page<>(query.getPageNum(), query.getPageSize()),
                new LambdaQueryWrapper<SysOauthClient>().like(StrUtil.isNotBlank(clientId), SysOauthClient::getClientId, clientId));
        return R.ok(page);
    }

    @ApiOperation(value = "客户端详情")
    @GetMapping("/{id}")
    public R<SysOauthClient> detail(@PathVariable @ApiParam("id") String id) {
        SysOauthClient client = sysOauthClientService.getById(id);
        return R.ok(client);
    }

    @ApiOperation(value = "新增客户端")
    @PostMapping
    public R<Object> add(@RequestBody SysOauthClient client) {
        boolean status = sysOauthClientService.save(client);
        return R.judge(status);
    }

    @ApiOperation(value = "修改客户端")
    @PutMapping(value = "/{id}")
    public R<Object> update(
            @PathVariable @ApiParam("id") String id,
            @RequestBody SysOauthClient client) {
        client.setClientId(id);
        boolean status = sysOauthClientService.updateById(client);
        return R.judge(status);
    }

    @ApiOperation(value = "删除客户端")
    @DeleteMapping("/{ids}")
    public R<Object> delete(@PathVariable("ids") @ApiParam("id集合,以,拼接字符串") String ids) {
        boolean status = sysOauthClientService.removeByIds(Arrays.asList(ids.split(StrConstant.COMMA)));
        return R.judge(status);
    }

    @ApiOperation(value = "根据客户端id获取客户端信息")
    @GetMapping("/clientId/{clientId}")
    @Override
    public R<SysOauthClient> getOAuthClientById(@PathVariable @ApiParam("客户端id") String clientId) {
        SysOauthClient client = sysOauthClientService.getOne(
                new LambdaQueryWrapper<SysOauthClient>().eq(SysOauthClient::getClientId, clientId), false);
        return R.ok(client);
    }

}