package cn.goduck.kl.auth.security.service.impl;

import cn.goduck.kl.admin.api.OAuthClientFeignClient;
import cn.goduck.kl.admin.entity.SysOauthClient;
import cn.goduck.kl.auth.enums.PasswordEncoderTypeEnum;
import cn.goduck.kl.common.core.base.R;
import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-07-03 14:58
 */
@Slf4j
@Service
@AllArgsConstructor
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private final OAuthClientFeignClient oAuthClientFeignClient;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        try {
            R<SysOauthClient> result = oAuthClientFeignClient.getOAuthClientById(clientId);
            log.info("远程调用：oAuthClientFeignClient.getOAuthClientById");
            // 判断远程调用查询是否成功
            if (R.isOk(result) && ObjectUtil.isNotNull(result.getData())) {
                SysOauthClient client = result.getData();
                BaseClientDetails clientDetails = new BaseClientDetails(
                        client.getClientId(),
                        client.getResourceIds(),
                        client.getScope(),
                        client.getAuthorizedGrantTypes(),
                        client.getAuthorities(),
                        client.getWebServerRedirectUri());
                clientDetails.setClientSecret(PasswordEncoderTypeEnum.NOOP.getPrefix() + client.getClientSecret());
                clientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
                clientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
                return clientDetails;
            } else {
                throw new NoSuchClientException("No client with requested id: " + clientId);
            }
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }
    }

}