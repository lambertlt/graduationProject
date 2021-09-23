package com.lambert.jpa.controller;

import com.lambert.jpa.model.User;
import com.lambert.jpa.service.UserService;
import com.lambert.jpa.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@RestController

public class UserController {
    @Autowired
    UserService userService;

    /**
     * showdoc
     *
     * @param id       可选 Long 用户id
     * @param username 可选 String 用户名
     * @return {"status":200,"data":{"data":{"id":1,"power":1,"username":"lambert","password":"********","accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true,"roles":[{"id":1,"name":"ROLE_admin","nameZh":"管理员"}],"authorities":[{"authority":"ROLE_admin"}]},"success":true,"message":"","status":200}}
     * @catalog 用户接口
     * @title 用户详情
     * @description 通过id或username查找用户详细信息
     * @method get
     * @url /user/identity/detail
     * @return_param status int 状态码
     * @return_param data String 数据
     * @remark null
     * @number null
     */
    @GetMapping("/user/identity/detail") // 查找用户接口，by id，username
    public void detail(@RequestParam(name = "username", required = false) String username, @RequestParam(name = "id", required = false) Long id, HttpServletResponse resp) throws IOException {
        Message message = userService.getUserDetail(username, id);
        message.returnJson(resp);
    }

    /**
     * showdoc
     *
     * @param "username" 必选 String 用户名
     * @param "password" 必选 String 密码
     * @return {"status":200,"data":{"data":{"id":1,"power":1,"username":"lambert","password":"********","accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true,"roles":[{"id":1,"name":"ROLE_admin","nameZh":"管理员"}],"authorities":[{"authority":"ROLE_admin"}]},"success":true,"message":"","status":200}}
     * @catalog 用户接口
     * @title 创建用户
     * @description 传入用户名、密码、即可创建用户,权限默认为 user
     * @method post
     * @url /tourist/identity/create
     * @return_param status int 状态码
     * @return_param data String 数据
     * @remark null
     * @number null
     */
    @PostMapping("/tourist/identity/create")
    public void create(@RequestBody User user, HttpServletResponse resp) throws IOException {
        Message message = userService.save(user);
        message.returnJson(resp);
    }

    /**
     * showdoc
     *
     * @param "id"       必选 Long 用户id
     * @param "password" 可选 String 密码
     * @param "power"    可选 Long 用户的权限 1-admin 或 2-user
     * @return {"status":200,"data":{"data":"","success":true,"message":"修改成功","status":200}}
     * @catalog 用户接口
     * @title 修改用户权限或密码
     * @description 传入用户id既可修改密码或权利，二者必选一
     * @method post
     * @url /user/identity/update
     * @return_param status int 状态码
     * @return_param data String 数据
     * @remark null
     * @number null
     */
    @PostMapping("/user/identity/update")
    public void update(@RequestBody User user, HttpServletResponse resp, Principal principal) throws IOException {
        Message message = userService.save(user);
        System.out.println(principal + "," + principal.getName());
        message.returnJson(resp);
    }

    /**
     * showdoc
     *
     * @param "id" 可选 Long 用户id
     * @return {"status":200,"data":{"data":"","success":true,"message":"删除成功","status":200}}
     * @catalog 用户接口
     * @title 删除用户
     * @description 通过id删除用户
     * @method get
     * @url /admin/identity/delete
     * @return_param status int 状态码
     * @return_param data String 数据
     * @remark null
     * @number null
     */
    @GetMapping("/admin/identity/delete")
    public void delete(Long id, HttpServletResponse resp) throws IOException {
        Message message = userService.delete(id);
        message.returnJson(resp);
    }

    /**
     * showdoc
     *
     * @param "username" 必选 String 用户名
     * @param "password" 必选 String 密码
     * @return {"status":200,"data":{"id":"1","username":"12154545"}}
     * @catalog 用户接口
     * @title 用户登陆
     * @description 传入用户名、密码、即可登陆, 请求方式：form表单提交.
     * @method post
     * @url /login
     * @return_param status int 状态码
     * @return_param data String 数据
     * @remark null
     * @number null
     */

    /**
     * showdoc
     *
     * @return "退出成功"
     * @catalog 用户接口
     * @title 用户退出
     * @description
     * @method get
     * @url /logout
     * @return_param status int 状态码
     * @return_param data String 数据
     * @remark null
     * @number null
     */

}
