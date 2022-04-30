package com.fei.springcloud.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {
    SUCCESS("0000","操作成功"),
    FAIL("1111","操作失败"),
    ERROR("9999","系统异常"),
    ;
    private String code;
    private String msg;

}
