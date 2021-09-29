package cn.goduck.kl.admin.service.impl;

import cn.goduck.kl.admin.entity.SysOauthClient;
import cn.goduck.kl.admin.mapper.SysOauthClientMapper;
import cn.goduck.kl.admin.query.SysOauthClientQuery;
import cn.goduck.kl.admin.service.SysOauthClientService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/24 15:30
 */
@Service
public class SysOauthClientServiceImpl extends ServiceImpl<SysOauthClientMapper, SysOauthClient> implements SysOauthClientService {

    @Override
    public IPage<SysOauthClient> page(SysOauthClientQuery sysOauthClientQuery) {
        String clientId = sysOauthClientQuery.getClientId();
        LambdaQueryWrapper<SysOauthClient> lambdaQueryWrapper = new LambdaQueryWrapper<SysOauthClient>()
                .like(StrUtil.isNotBlank(clientId), SysOauthClient::getClientId, clientId);
        return this.page(sysOauthClientQuery.page(), lambdaQueryWrapper);
    }

    @Override
    public SysOauthClient getOAuthClientById(String clientId) {
        return this.getOne(new LambdaQueryWrapper<SysOauthClient>().eq(SysOauthClient::getClientId, clientId), false);
    }

}