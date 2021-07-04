package cn.goduck.kl.auth.security.exception;

import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.core.constant.enums.ResultCode;
import cn.goduck.kl.common.web.util.HttpUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-07-04 15:35
 */
@Component
public class CustomerAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        HttpUtil.writer(response, HttpStatus.UNAUTHORIZED.value(), R.failed(ResultCode.CLIENT_AUTHENTICATION_FAILED));
    }

}
