package com.jeremy.weblog.common.aspect;

import com.jeremy.weblog.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Aspect
@Slf4j
public class ApiOperationLogAspect {

    @Pointcut("@annotation(com.jeremy.weblog.common.aspect.ApiOperationLog)")
    public void apiOperationLog(){}

    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        MDC.put("traceId", UUID.randomUUID().toString());

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();

        String jsonStr = Arrays.stream(args).map(toJsonStr()).collect(Collectors.joining(", "));

        String descreption = getApiOperationLogDescreption(joinPoint);
        log.info("===请求开始:【{}】, 入参: {}, 类名: {}, 方法名: {}=====",
                descreption, jsonStr,className, methodName);
        Object result = joinPoint.proceed();

        long excutionTime = System.currentTimeMillis() - startTime;
        log.info("===请求结束: {}, 耗时: {}, 出参: {}",
                descreption, excutionTime, JsonUtil.toJsonString(result));
        return result;

    }

    private String getApiOperationLogDescreption(ProceedingJoinPoint joinPoint){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        ApiOperationLog apiOperationLog = method.getAnnotation(ApiOperationLog.class);

        return apiOperationLog.description();
    }

    private Function<Object, String> toJsonStr(){
        return arg -> JsonUtil.toJsonString(arg);
    }
}
