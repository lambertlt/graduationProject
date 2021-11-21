package com.lambert.fun.new_app.controller;

import com.lambert.fun.new_app.entity.PersonalColumn;
import com.lambert.fun.new_app.service.PersonalColumnService;
import com.lambert.fun.new_app.util.Result;
import com.lambert.fun.new_app.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api.personalColumn")
public class PersonalColumnController {
    @Autowired
    PersonalColumnService personalColumnService;
    private Object msg;

    /*
     * 查找 通过专栏id
     * @Params Long id
     * */
    @GetMapping("get/id/{id}")
    ResponseEntity<Map> getPersonalColumnById(@PathVariable("id") Long id) {
        try {
            msg = personalColumnService.getPersonalColumnById(id);
            return ResponseEntity.ok(Result.ok(msg));
        } catch (Exception e) {
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 查找 通过专栏id
     * @Params Long userId
     * */
    @GetMapping("get/userId/{userId}")
    ResponseEntity<Map> getPersonalColumnByUserId(@PathVariable("userId") Long userId) {
        try {
            // TODO
            // 完成通过用户 id 查询专栏信息
//            msg = personalColumnService.getPersonalColumnById(userId);
            return ResponseEntity.ok(Result.ok(msg));
        } catch (Exception e) {
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 查找全部
     * */
    @GetMapping("get/all")
    ResponseEntity<Map> getPersonalColumnALl() {
        try {
            msg = personalColumnService.getPersonalColumnAll();
            return ResponseEntity.ok(Result.ok(msg));
        } catch (Exception e) {
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 创建
     * @Params Long id
     * */
    @PostMapping("post")
    ResponseEntity<Map> savePersonalColumn(@RequestBody PersonalColumn personalColumn) {
        try {
            msg = personalColumnService.newPersonalColumnSave(personalColumn);
            return ResponseEntity.ok(Result.ok(ResultCode.CREATED, msg));
        } catch (Exception e) {
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 删除
     * @Params Long id
     * */
    @PreAuthorize("hasRole('user')")
    @GetMapping("delete/id/{id}")
    ResponseEntity<Map> deleteByIdPersonalColumn(@PathVariable("id") Long id) {
        try {
            msg = personalColumnService.deletePersonalColumnById(id);
            return ResponseEntity.ok(Result.ok(ResultCode.NO_CONTENT, msg));
        } catch (Exception e) {
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 修改
     * @Params Long id
     * */
    @PreAuthorize("hasRole('user')")
    @PostMapping("patch")
    ResponseEntity<Map> updatePersonalColumn(@RequestBody PersonalColumn personalColumn) {
        try {
            msg = personalColumnService.updatePersonalColumn(personalColumn);
            return ResponseEntity.ok(Result.ok(ResultCode.CREATED, msg));
        } catch (Exception e) {
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }
}
