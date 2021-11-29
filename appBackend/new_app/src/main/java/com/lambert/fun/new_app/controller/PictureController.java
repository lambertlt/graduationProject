package com.lambert.fun.new_app.controller;

import com.lambert.fun.new_app.entity.Picture;
import com.lambert.fun.new_app.service.PictureService;
import com.lambert.fun.new_app.util.Result;
import com.lambert.fun.new_app.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api.picture")
public class PictureController {
    private Object msg = new Object();

    @Autowired
    PictureService pictureService;

    /*
     * 查找
     * @Params Long id
     * */
    @GetMapping("get/id/{id}")
    ResponseEntity<Map> getPictureById(@PathVariable("id") Long id) {
        try {
            msg = pictureService.getPictureById(id);
            return ResponseEntity.ok(Result.ok(ResultCode.OK, msg));
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 查找
     * @Params Long userId
     * */
    @GetMapping("get/userId/{userId}")
    ResponseEntity<Map> getPictureByUserId(@PathVariable("userId") Long userId) {
        try {
            msg = pictureService.getPictureByUserId(userId);
            return ResponseEntity.ok(Result.ok(ResultCode.OK, msg));
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 查找
     * @Params String type
     * */
    @GetMapping("get/type/{type}")
    ResponseEntity<Map> getPictureByType(@PathVariable("type") String type) {
        try {
            msg = pictureService.getPictureByType(type);
            return ResponseEntity.ok(Result.ok(ResultCode.OK, msg));
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }

    /*
     * 查找
     * @Params String type
     * @Params Long userId
     * */
    @GetMapping("get/type/{type}/userId/{userId}")
    ResponseEntity<Map> getPictureByTypeAndUserId(@PathVariable("type") String type, @PathVariable("userId") Long userId) {
        try {
            msg = pictureService.getPictureByTypeAndUserId(type, userId);
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
    ResponseEntity<Map> getPictureAll() {
        try {
            msg = pictureService.getPictureAll();
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
    ResponseEntity<Map> savePicture(@RequestBody Picture picture) {
        try {
            msg = pictureService.newPictureSave(picture);
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
    @PreAuthorize("hasAnyRole('user','admin')")
    @GetMapping("delete/id/{id}")
    ResponseEntity<Map> deleteByIdPicture(@PathVariable("id") Long id) {
        try {
            msg = pictureService.deletePictureById(id);
            System.out.println("msg" + msg);
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
    @PreAuthorize("hasAnyRole('user','admin')")
    @PostMapping("patch")
    ResponseEntity<Map> updatePicture(@RequestBody Picture picture) {
        try {
            msg = pictureService.updatePicture(picture);
            return ResponseEntity.ok(Result.ok(ResultCode.CREATED, msg));
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST));
        }
    }
}
