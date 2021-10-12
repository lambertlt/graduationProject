package com.lambert.jpa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EntityScan("com.motaoyu.spring.data.jpa.entity")
//@EnableJpaRepositories("com.motaoyu.spring.data.jpa.dao")
@MapperScan("com.lambert.jpa.mapper")
@SpringBootApplication
public class JpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

}
