package com.jeremy.weblog.common.utils;

import com.jeremy.weblog.common.exception.BaseExceptionInterface;
import com.jeremy.weblog.common.exception.BizException;
import lombok.Data;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable {
    private boolean success = true;
    private String message;
    private String errorCode;
    private T data;

    public static <T> Response<T> success(){
        Response response = new Response();
        response.setMessage("响应成功！！");
        return response;
    }

    public static <T> Response<T> success(T data){
        Response response = new Response();
        response.setMessage("响应成功！！");
        response.setData(data);
        return response;
    }
    public static <T> Response<T> fail(){
        Response response = new Response();
        response.setSuccess(false);
        return response;
    }

    public static <T> Response<T> fail(String errorMessge){
        Response response = new Response();
        response.setSuccess(false);
        response.setMessage(errorMessge);
        return response;
    }

    public static <T> Response<T> fail(String errorCode, String errorMassage){
        Response response = new Response();
        response.setSuccess(false);
        response.setMessage(errorMassage);
        response.setErrorCode(errorCode);
        return response;
    }

    public static <T> Response<T> fail(BizException bizException){
        Response response = new Response();
        response.setSuccess(false);
        response.setErrorCode(bizException.getErrorCode());
        response.setMessage(bizException.getErrorMessage());
        return response;
    }

    public static <T> Response<T> fail(BaseExceptionInterface baseExceptionInterface){
        Response response = new Response();
        response.setSuccess(false);
        response.setErrorCode(baseExceptionInterface.getErrorCode());
        response.setMessage(baseExceptionInterface.getErrorMessage());
        return response;
    }

}
