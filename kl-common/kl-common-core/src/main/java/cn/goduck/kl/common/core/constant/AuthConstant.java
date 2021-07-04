package cn.goduck.kl.common.core.constant;

/**
 * Desc: 认证常量
 * Author: Kon
 * Date: 2021/6/11 7:39
 */
public class AuthConstant {

    /**
     * 认证请求头key
     */
    public static final String AUTHORIZATION_KEY = "Authorization";

    /**
     * JWT令牌前缀
     */
    public static final String AUTHORIZATION_PREFIX = "bearer ";

    /**
     * Basic认证前缀
     */
    public static final String BASIC_PREFIX = "Basic ";

    /**
     * JWT载体key
     */
    public static final String JWT_PAYLOAD_KEY = "payload";

    /**
     * 用户Id
     */
    public static final String USER_ID_KEY = "userId";
    /**
     * 用户名
     */
    public static final String USER_NAME_KEY = "username";

    /**
     * 客户端Id
     */
    public static final String CLIENT_ID_KEY = "client_id";

    /**
     * 客户端密钥
     */
    public static final String CLIENT_SECRET_KEY = "client_secret";

    /**
     * JWT存储权限前缀
     */
    public static final String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    public static final String JWT_AUTHORITIES_KEY = "authorities";

    /**
     * 授权类型
     */
    public static final String GRANT_TYPE_KEY = "grant_type";

    /**
     * 刷新token
     */
    public static final String REFRESH_TOKEN = "refresh_token";

}