package com.lambert.fun.new_app.controller;

import com.lambert.fun.new_app.entity.CommentReply;
import com.lambert.fun.new_app.service.CommentReplyService;
import com.lambert.fun.new_app.util.Result;
import com.lambert.fun.new_app.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api.commentReply")
public class CommentReplyController {
    private Object msg = new Object();

    @Autowired
    CommentReplyService commentReplyService;

    /*
     * 查找
     * @Params Long id
     * */
    @GetMapping("get/id/{id}")
    ResponseEntity<Map> getCommentReplyById(@PathVariable("id") Long id) {
        try {
            msg = commentReplyService.getCommentReplyById(id);
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
    ResponseEntity<Map> getCommentReplyAll() {
        try {
            msg = commentReplyService.getCommentReplyAll();
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
    ResponseEntity<Map> saveCommentReply(@RequestBody CommentReply commentReply) {
        try {
            msg = commentReplyService.newCommentReplySave(commentReply);
            return ResponseEntity.ok(Result.ok(ResultCode.CREATED, msg));
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 删除
     * @Params Long id
     * */
    @GetMapping("delete/id/{id}")
    @PreAuthorize("hasAnyRole('user','admin')")
    ResponseEntity<Map> deleteByCommentReplyId(@PathVariable("id") Long id) {
        try {
            msg = commentReplyService.deleteCommentReplyById(id);
            return ResponseEntity.ok(Result.ok(ResultCode.NO_CONTENT, msg));
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }
}
