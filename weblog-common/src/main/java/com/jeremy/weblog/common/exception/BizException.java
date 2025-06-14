package com.jeremy.weblog.common.exception;

import lombok.Data;

@Data
public class BizException extends RuntimeException{
    private String errorCode;
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface){
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}
