package com.lambert.fun.new_app;

import com.lambert.fun.new_app.dao.*;
import com.lambert.fun.new_app.entity.*;
import com.lambert.fun.new_app.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class NewAppApplicationTests {
    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    PersonalColumnMapper personalColumnMapper;

    @Autowired
    ClassifyMapper classifyMapper;

    @Autowired
    MediaMapper mediaMapper;

    @Autowired
    ForeignDelete foreignDelete;

    @Test
    void contextLoads() {
        Object msg = mediaMapper.getLikeItNumber(1L);
        System.out.println(msg);
    }

    @Test
    void getPersonalColumn() {
        System.out.println(personalColumnMapper.getById(11L));
    }

    @Test
    void getClassify() {
        System.out.println(classifyMapper.getById(1L));
    }

    @Test
        // 项目初始化运行该方法，添加默认数据
    void init() {
        roleMapper.save(Role.getAdminInit());
        roleMapper.save(Role.getMediaInit());
        roleMapper.save(Role.getUserInit());

        List<Role> adminList = new ArrayList<>();
        List<Role> userList = new ArrayList<>();
        List<Role> mediaList = new ArrayList<>();
        adminList.add(Role.getAdmin());
        userList.add(Role.getUser());
        mediaList.add(Role.getMedia());
        User lambert = User.getUser("lambert", new BCryptPasswordEncoder().encode("123456"), 1L, adminList);
        User admin = User.getUser("admin", new BCryptPasswordEncoder().encode("123456"), 1L, adminList);
        User liziheng = User.getUser("liziheng", new BCryptPasswordEncoder().encode("123456"), 1L, adminList);
        User liulinboyi = User.getUser("liulinboyi", new BCryptPasswordEncoder().encode("123456"), 1L, adminList);
        userMapper.save(lambert);
        userMapper.save(admin);
        userMapper.save(liziheng);
        userMapper.save(liulinboyi);
    }
}
