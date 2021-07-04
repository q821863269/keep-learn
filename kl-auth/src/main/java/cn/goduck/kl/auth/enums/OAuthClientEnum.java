package cn.goduck.kl.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-06-20 15:46
 */
@Getter
@AllArgsConstructor
public enum OAuthClientEnum {

    ADMIN_CLIENT("admin", "系统管理端"),
    TEST_CLIENT("client", "测试客户端"),
    WX_APP_CLIENT("wx_app", "微信小程序端");

    private String clientId;

    private String desc;

    public static OAuthClientEnum getByClient(String clientId) {
        for (OAuthClientEnum client : OAuthClientEnum.values()) {
            if (client.getClientId().equals(clientId)) {
                return client;
            }
        }
        return null;
    }

}