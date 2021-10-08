package com.lambert.jpa.controller;

import com.lambert.jpa.model.Media;
import com.lambert.jpa.model.User;
import com.lambert.jpa.service.FileStorageService;
import com.lambert.jpa.util.Message;
import com.lambert.jpa.util.NonStaticResourceHttpRequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/file")
public class FileStorageController {
    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;
    @Autowired
    FileStorageService fileStorageService;

    /**
     * showdoc
     *
     * @param "id" 必选 Long 媒体文件id
     * @return {"status":200,"data":{"id":"1","username":"12154545"}}
     * @catalog 文件接口
     * @title 查看媒体文件详情
     * @description 访问权限：用户，管理员；
     * @method get
     * @url /file/findMediaById
     * @header token 可选 string 设备token
     * @return_param status int 状态码
     * @return_param data String 数据
     * @return_param success Boolean 是否成功
     * @return_param message String 消息
     * @remark null
     * @number null
     */
    @GetMapping("/findMediaById")
    public void findMediaById(HttpServletResponse resp, @RequestParam(name = "id") Long id) throws IOException {
        Message message;
        message = fileStorageService.findMediaById(id);
        message.returnJson(resp);
    }

    /**
     * showdoc
     *
     * @param "id" 必选 Long 用户id
     * @return {"data":[{"id":6,"title":"hello","classify":"hi1","path":"FileStorage/lambert/video_20210802_165149.mp4","type":"video/mp4","time":1633690586665,"size":40582600,"userId":1,"username":"lambert","fileName":"video_20210802_165149.mp4"}],"success":true,"message":"","status":200}
     * @catalog 文件接口
     * @title 用户全部文件
     * @description 访问权限：用户，管理员；
     * @method get
     * @url /file/findAllMediaByUserId
     * @header token 可选 string 设备token
     * @return_param status int 状态码
     * @return_param data String 数据
     * @return_param success Boolean 是否成功
     * @return_param message String 消息
     * @remark null
     * @number null
     */
    @GetMapping("/findAllMediaByUserId")
    public void findAllByUserId(@RequestParam(name = "id") Long id, HttpServletResponse resp) throws IOException {
        Message message;
        message = fileStorageService.findAllByUserId(id);
        message.returnJson(resp);
    }

    /**
     * showdoc
     *
     * @param "id"       必选 Long   媒体文件的id
     * @param "title"    必选 String 媒体文件的标题
     * @param "classify" 必选 String 媒体文件所属的分类
     * @return {"status":200,"data":{"id":"1","username":"12154545"}}
     * @catalog 文件接口
     * @title 媒体文件信息修改
     * @description 访问权限：用户，管理员；
     * @method post
     * @url /file/updateMedia
     * @header token 可选 string 设备token
     * @return_param status int 状态码
     * @return_param data String 数据
     * @return_param success Boolean 是否成功
     * @return_param message String 消息
     * @remark null
     * @number null
     */
    @PostMapping("/updateMedia")
    public void updateMedia(@RequestBody Media media, HttpServletResponse resp) throws IOException {
        Message message;
        System.out.println(media);
        message = fileStorageService.updateMedia(media);
        message.returnJson(resp);
    }

    /**
     * showdoc
     *
     * @param "file" 必选 char 选定一个文件
     * @return {"status":200,"data":{"id":"1","username":"12154545"}}
     * @catalog 文件接口
     * @title 文件上传
     * @description 访问权限：用户或管理员；
     * @method post
     * @url /file/upload
     * @return_param status int 状态码
     * @return_param data String 数据
     * @remark null
     * @number null
     */
    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file, HttpServletResponse resp, Authentication authentication) throws IOException {
        Message message;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        try {
            message = fileStorageService.save(file, user);
        } catch (Exception e) {
            message = new Message(false, 400, "文件上传失败", "");
        }
        message.returnJson(resp);
    }

    /**
     * showdoc
     *
     * @param "fileName" 必选 String 文件名称
     * @param "path"     必选 String 文件路径
     * @return {"status":200,"data":{"id":"1","username":"12154545"}}
     * @catalog 文件接口
     * @title 文件下载
     * @description 访问权限：用户；
     * @method get
     * @url /file/download
     * @return_param status int 状态码
     * @return_param data String 数据
     * @return_param success Boolean 是否成功
     * @return_param message String 消息
     * @remark null
     * @number null
     */
    @GetMapping("/download")
    public void download(@RequestParam(name = "fileName") String fileName, @RequestParam(name = "path") String path, HttpServletResponse resp) throws IOException {
        Message message = fileStorageService.download(fileName, path, resp);
//        message.returnJson(resp);
    }

    @GetMapping("/deleteById")
    public void deleteById(@RequestParam(name = "id") Long id, HttpServletResponse resp) throws IOException {
        Message message = fileStorageService.deleteById(id);
        message.returnJson(resp);
    }

    /**
     * showdoc
     *
     * @param 'path' 必选 String 媒体地址
     * @return {}
     * @catalog 音频接口
     * @title 播放
     * @description 播放权限：任意；播放视频或音频
     * @method get
     * @url /file/video/player
     * @return_param status int 状态码
     * @return_param data String 数据
     * @return_param success Boolean 是否成功
     * @return_param message String 消息
     * @remark
     * @number
     */
    @GetMapping("/video/player")
    public void videoPreview(@RequestParam(name = "path") String path, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Path filePath = Paths.get(path);
        if (Files.exists(filePath)) {
            String mimeType = Files.probeContentType(filePath);
            if (!StringUtils.isEmpty(mimeType)) {
                response.setContentType(mimeType);
            }
            request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }
}
