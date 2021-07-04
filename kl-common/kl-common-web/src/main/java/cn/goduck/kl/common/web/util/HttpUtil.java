package cn.goduck.kl.common.web.util;

import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.core.constant.GlobalConstant;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-07-04 13:04
 */
public class HttpUtil {

    public static void writer(HttpServletResponse response, int status, R<Object> result) throws IOException {
        response.setStatus(status);
        response.setCharacterEncoding(GlobalConstant.UTF8);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().print(JSONUtil.wrap(result, JSONConfig.create().setIgnoreNullValue(false)));
        response.getWriter().flush();
    }

}