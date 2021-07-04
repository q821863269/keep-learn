package cn.goduck.kl.auth.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-07-04 11:49
 */
@JsonSerialize(using = CustomerOAuth2ExceptionSerializer.class)
public class CustomerOAuth2Exception extends OAuth2Exception {

    @Getter
    @Setter
    private String errorCode;

    public CustomerOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomerOAuth2Exception(String msg) {
        super(msg);
    }

    public CustomerOAuth2Exception(String msg, Throwable t, String errorCode) {
        super(msg, t);
        this.errorCode = errorCode;
    }
    
}