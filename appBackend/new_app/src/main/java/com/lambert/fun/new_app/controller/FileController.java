package com.lambert.fun.new_app.controller;

import com.lambert.fun.new_app.entity.User;
import com.lambert.fun.new_app.service.FileService;
import com.lambert.fun.new_app.util.NonStaticResourceHttpRequestHandler;
import com.lambert.fun.new_app.util.Result;
import com.lambert.fun.new_app.util.ResultCode;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@AllArgsConstructor
@RequestMapping("api.file")
/*
 * 文件包括（用户头像、图片、音频、视频）
 * 所以上传要有各个对应的接口
 * */
public class FileController {
    //    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;
//    private Object msg;
//    @Autowired
//    FileService fileService;

    /*
     * 文件上传接口
     * 上传媒体，返回媒体信息
     * 上传图片，返回图片地址
     * */
    @PostMapping("post/type/{type}")
    public ResponseEntity uploadFile(@PathVariable("type") String type, @RequestParam("file") MultipartFile file, Authentication authentication) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = (User) principal;
//        if (file.isEmpty()) {
//            msg = "文件为空";
//            return ResponseEntity.ok(Result.ok(ResultCode.INVALID_REQUEST));
//        }
//        if (type == "media") {
//            msg = fileService.uploadMedia(file, user);
//        } else if (type == "img") {
//            msg = fileService.uploadImg();
//        } else {
//            return ResponseEntity.ok(Result.ok(ResultCode.INVALID_REQUEST));
//        }
        return ResponseEntity.ok(Result.ok(ResultCode.CREATED, null));
    }

    /**
     * 文件在线查看：包括（图片、视频、音频）
     */
    @GetMapping("online/get/path/{path}")
    public void videoPreview(@PathVariable("path") String path, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        Path filePath = Paths.get(path);
//        if (Files.exists(filePath)) {
//            String mimeType = Files.probeContentType(filePath);
//            if (!StringUtils.isEmpty(mimeType)) {
//                response.setContentType(mimeType);
//            }
//            request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
//            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
//        } else {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
//        }
    }


}
