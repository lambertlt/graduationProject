package com.lambert.app.controller;

import com.lambert.app.util.NonStaticResourceHttpRequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@AllArgsConstructor
@RequestMapping("/tourist/file")
public class FileRestController {
    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    /**
     * showdoc
     *
     * @param 'path' 必选 String 媒体地址
     * @return {"status":200,"data":{"id":"1",}}
     * @catalog 音频接口
     * @title 播放
     * @description 播放视频
     * @method get
     * @url /tourist/file
     * @remark
     * @number
     */
    @GetMapping("/video")
    public String videoPreview(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String path = request.getParameter("path");
//        /Users/lambert/IdeaProjects/app/media/mp4/video_20210802_165149.mp4
        Path filePath = Paths.get("/Users/lambert/IdeaProjects/app/media/mp4/video_20210802_165149.mp4");
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
        return "video";
    }
}
