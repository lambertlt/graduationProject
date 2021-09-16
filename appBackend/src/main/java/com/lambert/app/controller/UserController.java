package com.lambert.app.controller;

import com.lambert.app.mapper.UserMapper;
import com.lambert.app.model.Role;
import com.lambert.app.model.User;
import com.lambert.app.service.UserService;
import com.lambert.app.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    UserService userService;
    UserMapper userMapper;

    /**
     * showdoc
     *
     * @param id       可选 Long 用户id
     * @param username 可选 String 用户名
     * @return {"status":200,"data":{"id":"1","username":"12154545"}}
     * @catalog 用户接口
     * @title 用户详情
     * @description 通过id或username查找用户详细信息
     * @method get
     * @url /user/detail
     * @remark null
     * @number null
     */
    @GetMapping("/detail") // 查找用户接口，by id，username
    public HashMap detail(@RequestParam(name = "username", required = false) String username, @RequestParam(name = "id", required = false) Long id) {
        return userService.getUserDetail(username, id);
    }

    /**
     * showdoc
     *
     * @param user{"username"} 必选 String 用户名
     * @param user{"password"} 必选 String 密码
     * @param user{"power"}    必选 int 权利
     * @return {"status":200,"data":{"id":"1","username":"12154545"}}
     * @catalog 用户接口
     * @title 创建用户
     * @description 传入用户名、密码、权利即可创建用户
     * @method post
     * @url /user/detail
     * @remark null
     * @number null
     */
    @PostMapping("/create")
    public HashMap create(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * showdoc
     *
     * @param "id"       必选 Long 用户id
     * @param "username" 必选 String 用户名
     * @param "password" 必选 String 密码
     * @param "power"    必选 int 用户全部的信息
     * @return {"status":200,"data":{"id":"1","username":"12154545"}}
     * @catalog 用户接口
     * @title 修改用户
     * @description 传入用户名、密码、权利即可创建用户
     * @method post
     * @url /user/detail
     * @remark null
     * @number null
     */
    @PostMapping("/update")
    public HashMap update(@RequestBody User user) {
        return userService.createUser(user);
    }

}
