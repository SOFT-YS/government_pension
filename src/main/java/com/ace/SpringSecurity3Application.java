package com.ace;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ace.mapper")
public class SpringSecurity3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity3Application.class, args);
    }

}
