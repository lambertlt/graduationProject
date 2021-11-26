package com.lambert.fun.new_app.controller;

import com.lambert.fun.new_app.entity.Media;
import com.lambert.fun.new_app.service.MediaService;
import com.lambert.fun.new_app.util.Result;
import com.lambert.fun.new_app.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api.media")
public class MediaController {
    private Object msg = new Object();

    @Autowired
    MediaService mediaService;

    /*
     * 查找
     * @Params Long id
     * */
    @GetMapping("get/id/{id}")
    ResponseEntity<Map> getMediaById(@PathVariable("id") Long id) {
        try {
            msg = mediaService.getMediaById(id);
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
    ResponseEntity<Map> getMediaAll() {
        try {
            msg = mediaService.getMediaAll();
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
    ResponseEntity<Map> saveMedia(@RequestBody Media media) {
        try {
            msg = mediaService.newMediaSave(media);
            return ResponseEntity.ok(Result.ok(ResultCode.CREATED, msg));
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 更新点赞加1
     * @Params Long id
     * */
    @PostMapping("post/likeIt/add/id/{id}")
    ResponseEntity<Map> addLikeIt(@PathVariable("id") Long id) {
        try {
            msg = mediaService.updateAddLikeIt(id);
            return ResponseEntity.ok(Result.ok(ResultCode.CREATED, msg));
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 更新点赞减1
     * @Params Long id
     * */
    @PostMapping("post/likeIt/sub/id/{id}")
    ResponseEntity<Map> subLikeIt(@PathVariable("id") Long id) {
        try {
            msg = mediaService.updateSubLikeIt(id);
            return ResponseEntity.ok(Result.ok(ResultCode.CREATED, msg));
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 获取点赞数
     * @Params Long id
     * */
    @GetMapping("get/likeIt/id/{id}")
    ResponseEntity<Map> getLikeIt(@PathVariable("id") Long id) {
        try {
            msg = mediaService.getLikeIt(id);
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
//    TODO
    @PreAuthorize("hasAnyRole('user','admin')")
    @GetMapping("delete/id/{id}")
    ResponseEntity<Map> deleteByIdMedia(@PathVariable("id") Long id) {
        try {
            msg = mediaService.deleteMediaById(id);
            return ResponseEntity.ok(Result.ok(ResultCode.NO_CONTENT, msg));
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 修改
     * @Params Long id
     * */
//    TODO
    @PreAuthorize("hasAnyRole('user','admin')")
    @PostMapping("patch")
    ResponseEntity<Map> updateMedia(@RequestBody Media media) {
        try {
            msg = mediaService.updateMedia(media);
            return ResponseEntity.ok(Result.ok(ResultCode.CREATED, msg));
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }
}
