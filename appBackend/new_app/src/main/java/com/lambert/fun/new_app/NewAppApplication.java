package com.lambert.fun.new_app;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lambert.fun.new_app.dao")
public class NewAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewAppApplication.class, args);
    }

}
