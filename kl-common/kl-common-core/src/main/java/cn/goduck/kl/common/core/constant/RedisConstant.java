package cn.goduck.kl.common.core.constant;

/**
 * Desc: Redis常量
 * Author: Kon
 * Date: 2021/6/11 7:41
 */
public class RedisConstant {

    public static final String REDIS_AGREEMENT = "redis://";

    /**
     * 黑名单token前缀
     */
    public static final String AUTH_TOKEN_BLACKLIST_PREFIX = "auth_token:blacklist:";

    public static final String URL_PERM_ROLES_KEY = "system:url_perm:roles";
    public static final String BTN_PERM_ROLES_KEY = "system:btn_perm:roles";

    /**
     * 构造 Redis Key
     */
    public static String buildKey(String prefix, String... others) {
        return customBuildKey(prefix, StrConstant.COLON, others);
    }

    /**
     * 构造 Redis Key（自定义分隔符）
     */
    public static String customBuildKey(String prefix, String delimiter, String... others) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        for (String s : others) {
            sb.append(delimiter);
            sb.append(s);
        }
        return sb.toString();
    }

    // ==========================================Channel Topic==========================================

    /**
     * 清理角色本地缓存
     */
    public static final String CLEAN_ROLE_LOCAL_CACHE = "cleanRoleLocalCache";

}