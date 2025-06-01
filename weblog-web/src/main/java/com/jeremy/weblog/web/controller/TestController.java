package com.jeremy.weblog.web.controller;

import com.jeremy.weblog.common.aspect.ApiOperationLog;
import com.jeremy.weblog.common.utils.JsonUtil;
import com.jeremy.weblog.web.model.User;
import com.jeremy.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@Slf4j
@Api(tags = "首页模块")
public class TestController {
    @ApiOperationLog(description = "以及加上注解，开始打印日志")
    @PostMapping("/test")
    @ApiOperation(value = "测试接口")
    public Response test(@RequestBody @Validated User user){
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDate.now());
        user.setTime(LocalTime.now());
        log.info(JsonUtil.toJsonString(user));

//        if(bindResult.hasErrors()){
//            String errorMsg = bindResult.getFieldErrors()
//                    .stream()
//                    .map(FieldError::getDefaultMessage)
//                    .collect(Collectors.joining(", "));
//            return Response.fail(errorMsg);
//
//        }
//        return Response.success();

        return Response.success(user);
    }
}
