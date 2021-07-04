package cn.goduck.kl.auth.security.exception;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.stereotype.Component;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-07-04 11:17
 */
@Component
public class CustomerExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    private final ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) {
        Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);
        OAuth2Exception exception = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, causeChain);
        if (ObjectUtil.isNotNull(exception)) {
            // OAuth2异常
            return handleOAuth2Exception(new CustomerOAuth2Exception(e.getMessage(), e, exception.getOAuth2ErrorCode()));
        } else {
            // 其他异常
            return handleOAuth2Exception(new CustomerOAuth2Exception(e.getMessage(), e));
        }
    }

    private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) {
        // 获取状态码
        int status = e.getHttpErrorCode();
        // 设置Headers
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CACHE_CONTROL, "no-store");
        headers.set(HttpHeaders.PRAGMA, "no-cache");
        if (status == HttpStatus.UNAUTHORIZED.value() || (e instanceof InsufficientScopeException)) {
            headers.set(HttpHeaders.WWW_AUTHENTICATE, String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary()));
        }
        return new ResponseEntity<>(e, headers, HttpStatus.valueOf(status));
    }

}