package com.hjp123.lei.common.base.Exception;

import lombok.Data;

/**
 * @description: 自定义异常类
 * @author: Hjp
 * @time: 2020/7/27 14:01
 */

@Data
public class LeiException extends RuntimeException {

    private Integer code;

    public LeiException(Integer code, String message){
        super(message);
        this.code = code;
    }

}
