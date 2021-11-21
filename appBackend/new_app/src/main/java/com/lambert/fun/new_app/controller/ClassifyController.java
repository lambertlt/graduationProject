package com.lambert.fun.new_app.controller;

import com.lambert.fun.new_app.entity.Classify;
import com.lambert.fun.new_app.entity.User;
import com.lambert.fun.new_app.service.ClassifyService;
import com.lambert.fun.new_app.util.Result;
import com.lambert.fun.new_app.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api.classify")
public class ClassifyController {
    @Autowired
    ClassifyService classifyService;

    /*
     * 查找
     * @Params Long id
     * */
    @GetMapping("get/id/{id}")
    ResponseEntity<Map> getClassifyById(@PathVariable("id") Long id) {
        try {
            Object msg = classifyService.getClassifyById(id);
            return ResponseEntity.ok(Result.ok(msg));
        } catch (Exception e) {
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 查找全部
     * */
    @GetMapping("get/all")
    ResponseEntity<Map> getClassifyAll() {
        Object msg = classifyService.getClassifyAll();
        return ResponseEntity.ok(Result.ok(msg));
    }

    /*
     * 创建
     * @Params String name
     * @Params Date date
     * @Params Array personalColumnIdList
     * */
    @PostMapping("post")
    @PreAuthorize("hasRole('admin')")
    ResponseEntity<Map> saveClassify(@RequestBody Classify classify, Authentication authentication) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User nowUser = (User) principal;
        classify.setUser(nowUser);
        try {
            Object msg = classifyService.newClassifySave(classify);
            return ResponseEntity.ok(Result.ok(ResultCode.CREATED, msg));
        } catch (Exception e) {
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 删除
     * @Params Long id
     * */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("delete/id/{id}")
    ResponseEntity<Map> deleteByIdClassify(@PathVariable("id") Long id) {
        try {
            Object msg = classifyService.deleteClassifyById(id);
            return ResponseEntity.ok(Result.ok(ResultCode.NO_CONTENT, msg));
        } catch (Exception e) {
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 修改
     * @Params Long id
     * */
    @PreAuthorize("hasRole('admin')")
    @PostMapping("patch")
    ResponseEntity<Map> updateClassify(@RequestBody Classify classify, Authentication authentication) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User nowUser = (User) principal;
        classify.setUser(nowUser);
        try {
            Object msg = classifyService.updateClassify(classify);
            return ResponseEntity.ok(Result.ok(ResultCode.CREATED, msg));
        } catch (Exception e) {
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }
}
