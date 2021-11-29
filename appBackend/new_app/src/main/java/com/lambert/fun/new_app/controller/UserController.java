package com.lambert.fun.new_app.controller;

import com.lambert.fun.new_app.entity.User;
import com.lambert.fun.new_app.service.UserService;
import com.lambert.fun.new_app.util.Result;
import com.lambert.fun.new_app.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api.user")
public class UserController {
    @Autowired
    UserService userService;

    private Object msg = new Object();

    /*
     * 用户查找
     * @Params Long id
     * */
    @GetMapping("get/id/{id}")
    ResponseEntity<Map> getUserId(@PathVariable(value = "id") Long id) {
        if (id == null)
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        try {
            msg = userService.loadUserByUserid(id);
            ((User) msg).setPassword("********");
            return ResponseEntity.ok(Result.ok(msg));
        } catch (Exception e) {
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 用户查找
     * @Params String name
     * */
    @GetMapping("get/name/{name}")
    ResponseEntity<Map> getUserName(@PathVariable(value = "name", required = false) String name) {
        if (name == null)
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        try {
            msg = userService.loadUserByUsername(name);
            ((User) msg).setPassword("********");
            return ResponseEntity.ok(Result.ok(msg));
        } catch (Exception e) {
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 用户创建
     * @Params String username
     * @Params String password
     * */
    @PostMapping("post")
    ResponseEntity<Map> saveUser(@RequestBody User user) {
        msg = userService.newUserSave(user);
        if (msg.equals("用户已存在"))
            return ResponseEntity.ok(Result.ok(ResultCode.INVALID_REQUEST, msg));
        ((User) msg).setPassword("********");
        return ResponseEntity.ok(Result.ok(ResultCode.CREATED, msg));
    }

    /*
     * 用户删除
     * @Params Long id
     * */
    @PreAuthorize("principal.id.equals(#id) or hasAnyRole('admin')")
    @GetMapping("delete/id/{id}")
    ResponseEntity<Map> deleteUser(@PathVariable("id") Long id) {
        try {
            msg = userService.deleteUser(id);
            return ResponseEntity.ok(Result.ok(ResultCode.NO_CONTENT, msg));
        } catch (Exception e) {
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    // todo 修改用户个人信息

    /*
     * 用户或管理员修改密码
     * @Params Long id
     * @Params String password
     * */
    @PreAuthorize("principal.id.equals(#user.id) or hasAnyRole('admin')")
    @PostMapping("patch/password")
    ResponseEntity<Map> updateUserPassword(@RequestBody User user) {
        if (user.getId() == null && user.getPassword() == null) {
            // 用户参数错误，没有id，或缺少要修改的内容
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
        try {
            msg = userService.updateUserPassword(user);
            ((User) msg).setPassword("********");
            return ResponseEntity.ok(Result.ok(ResultCode.CREATED, msg));
        } catch (Exception e) {
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 管理员修改权限
     * @Params Long id
     * @Params Long power
     * */
    @PreAuthorize("hasAnyRole('admin')")
    @PostMapping("patch/power")
    ResponseEntity<Map> updateUserPower(@RequestBody User user) {
        if (user.getId() != null && user.getPower() != null) {
            // 参数不为空可以修改power
            msg = userService.updateUserPower(user);
            ((User) msg).setPassword("********");
            return ResponseEntity.ok(Result.ok(ResultCode.CREATED, msg));
        } else {
            // 请求的参数不对
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }
}
