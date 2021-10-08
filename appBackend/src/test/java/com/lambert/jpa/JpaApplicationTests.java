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
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        long now1 = System.currentTimeMillis();
        long now2 = new Date().getTime();
        long time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2018-06-30 20:00:00", new ParsePosition(0)).getTime() / 1000;
        System.out.println("获取指定时间的时间戳:" + time);
        System.out.println("当前时间戳:" + now1);
        System.out.println("当前时间戳:" + now2);
        System.out.println(new Date());
    }

}
