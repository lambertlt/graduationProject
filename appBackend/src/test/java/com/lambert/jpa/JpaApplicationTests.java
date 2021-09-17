package com.lambert.jpa;

import com.lambert.jpa.mapper.ClassifyMapper;
import com.lambert.jpa.mapper.RoleMapper;
import com.lambert.jpa.model.Classify;
import com.lambert.jpa.model.Role;
import com.lambert.jpa.model.User;
import com.lambert.jpa.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletResponse;

@SpringBootTest
class JpaApplicationTests {
    @Autowired
    ClassifyMapper classifyMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
//        userService.getUserDetail("", 1L);
    }

}
