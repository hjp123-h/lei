package com.hjp123.lei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 * @description: 启动类
 * @author: Hjp
 * @time: 2020/9/3 19:00
 * @EnableDiscoveryClient nacos服务注册
 * @EnableFeignClients 服务调用
 */

@SpringBootApplication(scanBasePackages = "com.hjp123.lei")
@EnableDiscoveryClient
@EnableFeignClients
public class LeiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeiApplication.class, args);
    }

}
