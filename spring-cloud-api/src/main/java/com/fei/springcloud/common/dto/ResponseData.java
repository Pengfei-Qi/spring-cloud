package com.fei.springcloud.common.dto;

import com.fei.springcloud.common.enums.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/*
 * @Author qpf
 * @Date 2022/4/30 10:48
 * @Description: 返回值封装
 *
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
public class ResponseData<T> {
    private String code;
    private String msg;
    private T data;

    /*
     * @Author qpf
     * @Date 2022/4/30 11:37
     * @Description: 自定义返回值
     *
     **/
    public static <T> ResponseData<T> response(ErrorCodeEnum errorCodeEnum, T data){
        return new ResponseData(errorCodeEnum.getCode(),errorCodeEnum.getMsg(),data);
    }
}
