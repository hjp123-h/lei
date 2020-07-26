package com.hjp123.lei.common.code;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 数据格式类
 * @author: Hjp
 * @time: 2020/7/9 19:01
 */

@Data
public class R {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    /**
     *
     * @description: 对象私有化
     * @author: Hjp
     * @time: 2020/7/9 19:09
     */

    private R(){}

    /**
     * @description: 静态成功方法
     * @return: R对象
     * @author: Hjp
     * @time: 2020/7/9 19:09
     */

    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    /**
     * @description: 静态失败方法
     * @return: R对象
     * @author: Hjp
     * @time: 2020/7/9 19:09
     */

    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    /**
     * @param success(成功失败)
     * @description: 链式编程，方便外面调用
     * @return: 当前R对象
     * @author: Hjp
     * @time: 2020/7/9 19:10
     */

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
