package cn.goduck.kl.common.core.util;

import cn.goduck.kl.common.core.constant.AuthConstant;
import cn.goduck.kl.common.core.constant.StrConstant;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Desc: Jwt工具类
 * Author: Kon
 * Date: 2021/6/17 13:37
 */
public class JwtUtil {

    /**
     * 获取登录认证的客户端id
     * <p>
     * 兼容两种方式获取Oauth2客户端信息（client_id、client_secret）
     * 方式一：client_id、client_secret放在请求路径中
     * 方式二：放在请求头（Request Headers）中的Authorization字段，且经过加密
     */
    public static String getOAuthClientId() {
        String clientId;
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // 从请求路径中获取
        clientId = request.getParameter(AuthConstant.CLIENT_ID_KEY);
        if (StrUtil.isNotBlank(clientId)) {
            return clientId;
        }
        // 从请求头获取
        String basic = request.getHeader(AuthConstant.AUTHORIZATION_KEY);
        if (StrUtil.isNotBlank(basic) && basic.startsWith(AuthConstant.BASIC_PREFIX)) {
            basic = basic.replace(AuthConstant.BASIC_PREFIX, StrConstant.EMPTY);
            String basicPlainText = new String(Base64.getDecoder().decode(basic));
            clientId = basicPlainText.split(StrConstant.COLON)[0];
        }
        return clientId;
    }

    @SneakyThrows
    public static JSONObject getJwtPayload() {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String jwtPayload = request.getHeader(AuthConstant.JWT_PAYLOAD_KEY);
        return JSONUtil.parseObj(URLUtil.decode(jwtPayload, CharsetUtil.CHARSET_UTF_8));
    }

    public static Long getUserId() {
        return getJwtPayload().getLong(AuthConstant.USER_ID_KEY);
    }

    public static String getUsername() {
        return getJwtPayload().getStr(AuthConstant.USER_NAME_KEY);
    }

    @SuppressWarnings("unchecked")
    public static List<String> getRoles() {
        JSONObject payload = getJwtPayload();
        if (payload.size() > 0) {
            List<String> list = payload.get(AuthConstant.JWT_AUTHORITIES_KEY, List.class);
            return new ArrayList<>(list);
        }
        return null;
    }

}