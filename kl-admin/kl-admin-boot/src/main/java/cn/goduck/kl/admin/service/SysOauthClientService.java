package cn.goduck.kl.admin.service;

import cn.goduck.kl.admin.entity.SysOauthClient;
import cn.goduck.kl.admin.query.SysOauthClientQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/6/24 15:30
 */
public interface SysOauthClientService extends IService<SysOauthClient> {

    IPage<SysOauthClient> page(SysOauthClientQuery sysOauthClientQuery);

    SysOauthClient getOAuthClientById(String clientId);

}