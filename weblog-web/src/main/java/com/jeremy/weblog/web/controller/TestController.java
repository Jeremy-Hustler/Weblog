package com.jeremy.weblog.web.controller;

import com.jeremy.weblog.common.aspect.ApiOperationLog;
import com.jeremy.weblog.web.model.User;
import com.jeremy.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class TestController {
    @ApiOperationLog(description = "以及加上注解，开始打印日志")
    @PostMapping("/test")
    public Response test(@RequestBody @Validated User user){
//        if(bindResult.hasErrors()){
//            String errorMsg = bindResult.getFieldErrors()
//                    .stream()
//                    .map(FieldError::getDefaultMessage)
//                    .collect(Collectors.joining(", "));
//            return Response.fail(errorMsg);
//
//        }
//        return Response.success();

        return Response.success();
    }
}
