package cn.goduck.kl.admin.api;

import cn.goduck.kl.admin.entity.SysOauthClient;
import cn.goduck.kl.common.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-07-03 15:16
 */
@FeignClient(value = "kl-admin", contextId = "oAuthClientFeignClient")
@RequestMapping("/oauthClients")
public interface OAuthClientFeignClient {

    @GetMapping("/clientId/{clientId}")
    R<SysOauthClient> getOAuthClientById(@PathVariable("clientId") String clientId);

}