package cn.goduck.kl.common.core.config;

import cn.goduck.kl.common.core.constant.DateConstant;
import cn.goduck.kl.common.core.jackson.JavaTimeModule;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-06-13 13:00
 */
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return buildObjectMapper();
    }

    public static ObjectMapper buildObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(TimeZone.getTimeZone(DateConstant.TIME_ZONE));
        objectMapper.setDateFormat(new SimpleDateFormat(DateConstant.DEFAULT_DATE_TIME_FORMAT));
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // 后台Long值传递给前端精度丢失问题
        javaTimeModule.addSerializer(Long.class, ToStringSerializer.instance);
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

}