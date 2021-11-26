package com.lambert.fun.new_app.controller;

import com.lambert.fun.new_app.entity.Comment;
import com.lambert.fun.new_app.service.CommentService;
import com.lambert.fun.new_app.util.Result;
import com.lambert.fun.new_app.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api.comment")
public class CommentController {
    private Object msg = new Object();

    @Autowired
    CommentService commentService;

    /*
     * 查找
     * @Params Long id
     * */
    @GetMapping("get/id/{id}")
    ResponseEntity<Map> getCommentById(@PathVariable("id") Long id) {
        try {
            msg = null;
            return ResponseEntity.ok(Result.ok(ResultCode.OK, msg));
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 查找全部
     * */
    @GetMapping("get/all")
    ResponseEntity<Map> getCommentAll() {
        try {
            msg = null;
            return ResponseEntity.ok(Result.ok(ResultCode.OK, msg));
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 创建
     * @Params Long id
     * */
    @PostMapping("post")
    @PreAuthorize("hasAnyRole('user','admin')")
    ResponseEntity<Map> saveComment(@RequestBody Comment comment) {
        try {
            msg = commentService.newCommentSave(comment);
            return ResponseEntity.ok(Result.ok(ResultCode.OK, msg));
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST, comment.toString()));
        }
    }

    /*
     * 删除
     * @Params Long id
     * */
    @GetMapping("delete/id/{id}")
    @PreAuthorize("hasAnyRole('user','admin')")
    ResponseEntity<Map> deleteByIdComment(@PathVariable("id") Long id) {
        try {
            msg = null;
            return ResponseEntity.ok(Result.ok(ResultCode.OK, msg));
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 修改
     * @Params Long id
     * */
    @PostMapping("patch")
    @PreAuthorize("hasAnyRole('user','admin')")
    ResponseEntity<Map> updateComment(@RequestBody Comment comment) {
        try {
            msg = null;
            return ResponseEntity.ok(Result.ok(ResultCode.OK, msg));
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }
}
