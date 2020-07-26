package com.hjp123.lei.service.edu.controller;


import com.hjp123.lei.common.code.R;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 登陆
 * @author: Hjp
 * @time: 2020/7/15 16:21
 */

@RestController
@RequestMapping("/edu/user")
@CrossOrigin
public class EduLoginController {

    /**
     *
     * @description: 登陆方法
     * @author: Hjp
     * @time: 2020/7/15 16:24
     */

    @PostMapping("/login")
    public R login(){

        return R.ok().data("token","admin");
    }

    /**
     *
     * @description: 获取用户信息
     * @author: Hjp
     * @time: 2020/7/15 16:24
     */

    @GetMapping("/info")
    public R info(){

        return R.ok().data("roles","管理员").data("name","韩佳鹏").data("avatar","https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg");
    }
}
