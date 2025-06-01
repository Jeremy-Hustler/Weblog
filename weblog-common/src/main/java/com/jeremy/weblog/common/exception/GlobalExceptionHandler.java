package com.jeremy.weblog.common.exception;

import com.jeremy.weblog.common.enums.ResponseCodeEnum;
import com.jeremy.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public Response handleBizException(HttpServletRequest request, BizException e){
        log.warn("{} request fail,errorCode is{},errorMessage is{}", request.getRequestURI(), e.getErrorCode(), e.getErrorMessage());
        return Response.fail(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response handleOtherException(HttpServletRequest request, Exception e){
       log.warn("{} request fail,exception is {}", request.getRequestURI(), e);
        return Response.fail(ResponseCodeEnum.SYSTEM_ERROR_CODE );
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public Response handleMANVException(HttpServletRequest request, MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        String errorCode = ResponseCodeEnum.PARAM_NOT_VALIED.getErrorCode();
        StringBuilder sb = new StringBuilder();
        System.out.println(bindingResult.getFieldErrors());
        Optional.ofNullable(bindingResult.getFieldErrors()).ifPresent(
                errors -> {
                    errors.forEach(error -> sb.append(error.getField())
                            .append(" ")
                            .append(error.getDefaultMessage())
                            .append("当前值为：")
                            .append(error.getRejectedValue()));
                }
        );
        String errorMessage = sb.toString();
        log.warn("{} request fail,errorCode is {}, errorMessage is {}", request.getRequestURI(), errorCode, errorMessage);
        return Response.fail(errorCode, errorMessage);
    }
}
