package cn.goduck.kl.auth.security.filter;

import cn.goduck.kl.common.core.base.R;
import cn.goduck.kl.common.core.constant.AuthConstant;
import cn.goduck.kl.common.core.constant.StrConstant;
import cn.goduck.kl.common.core.constant.enums.ResultCode;
import cn.goduck.kl.common.web.util.HttpUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-07-04 12:51
 */
@Component
public class ClientDetailsAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 只有获取token的时候需要携带携带客户端信息，放过其他
        if (!request.getRequestURI().equals("/oauth/token")) {
            filterChain.doFilter(request, response);
            return;
        }
        String[] clientDetails = this.isHasClientDetails(request);
        if (clientDetails == null) {
            HttpUtil.writer(response, HttpStatus.UNAUTHORIZED.value(), R.failed(ResultCode.REQUEST_NOT_FOUND_CLIENT_DETAILS));
            return;
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 判断请求头中是否包含client信息，不包含返回null  Base64编码
     */
    private String[] isHasClientDetails(HttpServletRequest request) {
        String basic = request.getHeader(AuthConstant.AUTHORIZATION_KEY);
        if (StrUtil.isNotBlank(basic) && basic.startsWith(AuthConstant.BASIC_PREFIX)) {
            basic = basic.replace(AuthConstant.BASIC_PREFIX, StrConstant.EMPTY);
            String basicPlainText = new String(Base64.getDecoder().decode(basic));
            String[] clientArrays = basicPlainText.split(StrConstant.COLON);
            if (clientArrays.length == 2) {
                return clientArrays;
            }
        }
        return null;
    }

}
