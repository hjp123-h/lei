package com.hjp123.lei.common.base.excptionhandle;


import com.hjp123.lei.common.code.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 统一异常处理
 * @author: Hjp
 * @time: 2020/7/11 16:02
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("出错了 请稍后再试~");
    }
}
