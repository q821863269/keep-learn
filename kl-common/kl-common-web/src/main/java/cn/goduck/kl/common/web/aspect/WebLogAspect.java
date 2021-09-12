package cn.goduck.kl.common.web.aspect;

import cn.goduck.kl.common.core.constant.StrConstant;
import cn.goduck.kl.common.core.util.JwtUtil;
import cn.goduck.kl.common.web.util.IpUtil;
import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Desc:
 * Author: Kon
 * Date: 2021-06-20 11:34
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Resource
    private ObjectMapper objectMapper;

    @Pointcut("execution( * cn.goduck.kl..controller..*.*(..))")
    public void webLog() {
    }

    @Around(value = "webLog()")
    public Object recordWebLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;
        // 创建定时器
        StopWatch stopWatch = new StopWatch();
        // 开始计时器
        stopWatch.start();
        // 执行业务逻辑并获取返回值，这里不需要去处理异常
        result = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        // 计时结束
        stopWatch.stop();

        // 获取请求上下文
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtil.isNotNull(requestAttributes)) {
            HttpServletRequest request = requestAttributes.getRequest();

            // 请求的IP地址
            String ip = IpUtil.getIpAddr(request);
            // 请求的城市信息
            String region = IpUtil.getCityInfo(ip);
            // 请求的url地址
            String url = request.getRequestURL().toString();
            // 请求方法类型
            String methodType = request.getMethod();

            // 处理方法
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = methodSignature.getMethod();
            // 处理方法上的ApiOperation注解
            ApiOperation annotation = method.getAnnotation(ApiOperation.class);
            // 处理对象的类名称
            String className = proceedingJoinPoint.getTarget().getClass().getName();

            // 处理方法路径
            String handlerMethodPath = className + StrConstant.DOT + method.getName();
            // 处理方法描述
            String description = ObjectUtil.isNull(annotation) ? StrConstant.EMPTY : annotation.value();
            // 请求参数
            Object parameter = getMethodParameter(method, proceedingJoinPoint.getArgs());
            // 请求结果 (result)
            // 请求处理耗时
            long spendTime = stopWatch.getTotalTimeMillis();
            // 请求用户
            String username = JwtUtil.getUsername();

            Map<String, Object> webLogMap = new LinkedHashMap<>(10);
            webLogMap.put("ip", ip);
            webLogMap.put("region", region);
            webLogMap.put("url", url);
            webLogMap.put("methodType", methodType);
            webLogMap.put("handlerMethodPath", handlerMethodPath);
            webLogMap.put("description", description);
            webLogMap.put("parameter", parameter);
            webLogMap.put("result", result);
            webLogMap.put("spendTime", spendTime + "ms");
            webLogMap.put("username", username);

            // 格式化json
            //String webLog = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(webLogMap);
            // 没有格式化json
            String webLog = objectMapper.writeValueAsString(webLogMap);
            log.info(webLog);
        } else {
            log.error("请求上下文对象为空，无法记录WebLog");
        }

        return result;
    }

    /**
     * 获取方法参数
     */
    private Object getMethodParameter(Method method, Object[] args) {
        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = localVariableTableParameterNameDiscoverer.getParameterNames(method);
        HashMap<String, Object> methodParameters = new HashMap<>();
        if (ObjectUtil.isNotNull(args) && ObjectUtil.isNotNull(parameterNames)) {
            for (int i = 0; i < parameterNames.length; i++) {
                if (args[i] instanceof HttpServletRequest) {
                    methodParameters.put(parameterNames[i], HttpServletRequest.class.getName());
                } else if (args[i] instanceof HttpServletResponse) {
                    methodParameters.put(parameterNames[i], HttpServletResponse.class.getName());
                } else if (args[i] instanceof MultipartFile) {
                    methodParameters.put(parameterNames[i], MultipartFile.class.getName());
                } else {
                    methodParameters.put(parameterNames[i], ObjectUtil.isNull(args[i]) ? StrConstant.EMPTY : args[i]);
                }
            }
        }
        return methodParameters;
    }

}