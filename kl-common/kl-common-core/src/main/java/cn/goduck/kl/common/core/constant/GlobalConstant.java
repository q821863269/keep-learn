package cn.goduck.kl.common.core.constant;

/**
 * Desc: 全局常量
 * Author: Kon
 * Date: 2021/6/11 7:40
 */
public class GlobalConstant {

    /**
     * 编码
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 自动填充字段
     */
    public static final String CREATE_BY = "createBy";
    public static final String CREATE_TIME = "createTime";
    public static final String UPDATE_BY = "updateBy";
    public static final String UPDATE_TIME = "updateTime";

    /**
     * 有效状态
     */
    public static final Integer VALID = 0;

    /**
     * 失效状态
     */
    public static final Integer INVALID = 1;

    /**
     * 用户默认密码
     */
    public static final String DEFAULT_USER_PASSWORD = "123456";

    /**
     * ROOT角色Code
     */
    public static final String ROOT_ROLE_CODE = "ROOT";

    /**
     * 根部门ID
     */
    public static final Long DEPT_ROOT_ID = 0L;

    /**
     * 根菜单ID
     */
    public static final Long MENU_ROOT_ID = 0L;

    /**
     * 移动端请求
     */
    public static final String APP_API_PATTERN = "/*/app-api/**";

}