package com.hjp123.lei.service.edu.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description: 配置类
 * @author: Hjp
 * @time: 2020/7/2 15:05
 */


@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.hjp123.lei.service.edu.mapper")
@ComponentScan(basePackages = {"com.hjp123.lei"})
public class MyBatisPlusConfig {

    /**
     *
     * @description: 逻辑删除
     * @author: Hjp
     * @time: 2020/7/24 14:55
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     *
     * @description: 分页插件
     * @author: Hjp
     * @time: 2020/7/9 19:26
     */

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
