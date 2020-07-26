package com.hjp123.lei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hjp123.lei")
public class LeiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeiApplication.class, args);
    }

}
