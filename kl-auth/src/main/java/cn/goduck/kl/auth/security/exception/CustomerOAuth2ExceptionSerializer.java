package cn.goduck.kl.auth.security.exception;

import cn.goduck.kl.common.core.constant.enums.ResultCode;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * Desc: OAuth2 异常格式化
 * Author: Kon
 * Date: 2021-07-04 11:51
 */
public class CustomerOAuth2ExceptionSerializer extends StdSerializer<CustomerOAuth2Exception> {

    public CustomerOAuth2ExceptionSerializer() {
        super(CustomerOAuth2Exception.class);
    }

    @Override
    public void serialize(CustomerOAuth2Exception e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("code", ResultCode.FAIL.getCode());
        jsonGenerator.writeObjectField("msg", e.getMessage());
        jsonGenerator.writeObjectField("data", e.getErrorCode());
        jsonGenerator.writeEndObject();
    }

}