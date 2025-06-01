package com.jeremy.weblog.common.enums;

import com.jeremy.weblog.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {
    SYSTEM_ERROR_CODE("10000", "后台小哥正在修复~"),
    PRODUCT_NOT_FOUND("20000", "产品未找到！"),
    PARAM_NOT_VALIED("10001", "参数校验错误！"),
    ;
    private final String errorCode;
    private final String errorMessage;
}
