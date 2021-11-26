package com.lambert.fun.new_app.controller;

import com.lambert.fun.new_app.entity.User;
import com.lambert.fun.new_app.service.FileService;
import com.lambert.fun.new_app.util.FileUtil;
import com.lambert.fun.new_app.util.NonStaticResourceHttpRequestHandler;
import com.lambert.fun.new_app.util.Result;
import com.lambert.fun.new_app.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/*
 * 文件包括（用户头像、图片、音频、视频）
 * 所以上传要有各个对应的接口
 * */
@RestController
@RequestMapping("api.file")
public class FileController {
    private Object msg = new Object();
    @Autowired
    private NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    @Autowired
    FileService fileService;


    /*
     * 用户上传图片
     * */
    @PreAuthorize("hasAnyRole('user','admin')")
    @PostMapping("post/image/type/{type}")
    public ResponseEntity<Map> uploadImage(@PathVariable("type") String type, @RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST, "文件为空"));
        }
        try {
            fileService.uploadImage(type, multipartFile);
            return ResponseEntity.ok(Result.ok(ResultCode.CREATED, "Upload file successfully: " + multipartFile.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Result.ok("Could not upload the file:" + multipartFile.getOriginalFilename()));
        }
    }

    /*
     * 用户上传媒体
     * */
    @PreAuthorize("hasAnyRole('user','admin')")
    @PostMapping("post/media")
    public ResponseEntity<Map> uploadMedia(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.ok(Result.no(ResultCode.INVALID_REQUEST, "文件为空"));
        }
        try {
            fileService.uploadMedia(multipartFile);
            return ResponseEntity.ok(Result.ok(ResultCode.CREATED, "Upload file successfully: " + multipartFile.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Result.ok("Could not upload the file:" + multipartFile.getOriginalFilename()));
        }
    }

//    @GetMapping("/files")
//    public ResponseEntity<List<UploadFile>> files() {
//        List<UploadFile> files = fileService.load()
//                .map(path -> {
//                    String fileName = path.getFileName().toString();
//                    String url = MvcUriComponentsBuilder
//                            .fromMethodName(FileUploadController.class,
//                                    "getFile",
//                                    path.getFileName().toString()
//                            ).build().toString();
//                    return new UploadFile(fileName, url);
//                }).collect(Collectors.toList());
//        return ResponseEntity.ok(files);
//    }

    /*
     * 文件下载 通过媒体文件id，后台获取文件真实地址
     * "fileStorage/lambert/images/归处.png"
     * */
    @PreAuthorize("hasAnyRole('admin','user')")
    @GetMapping("get/type/{type}/id/{id}")
    public ResponseEntity<InputStreamResource> loadFileById(@PathVariable("type") String type, @PathVariable("id") Long id) throws IOException {
//      String path1 = "/Users/lambert/IdeaProjects/new_app/fileStorage/lambert/images/归处.png";
        String path = (String) fileService.getFilePath(type, id);
        return FileUtil.loadFile(path);
    }

    /*
     * 文件下载
     * "fileStorage/lambert/images/归处.png"
     * path 需要为绝对路径
     * */
    @PreAuthorize("hasAnyRole('admin','user')")
    @GetMapping("get/path/{path}")
    public ResponseEntity<InputStreamResource> loadFileByPath(@PathVariable("path") String path) throws IOException {
//      String path1 = "/Users/lambert/IdeaProjects/new_app/fileStorage/lambert/images/归处.png";
        return FileUtil.loadFile(path);
    }

    /*
     * 在线预览，通过文件地址
     * */
    @GetMapping("get/online/path/{path}")
    public void online(HttpServletRequest request, HttpServletResponse response, @PathVariable String path) throws IOException, ServletException {
        String path1 = "/Users/lambert/IdeaProjects/new_app/fileStorage/lambert/images/归处.png";
        Path filePath = Paths.get(path1);
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
