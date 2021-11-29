package com.lambert.fun.new_app.util;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

public class FileUtil {
    private static Object msg = new Object();

    public static Object deleteFile(String path) {
        try {
            File file = new File(path);
            file.delete();
            msg = "删除 " + path + " 文件成功";
        } catch (Exception e) {
            msg = e;
        }
        return msg;
    }

    // 用于文件下载
    public static ResponseEntity<InputStreamResource> loadFile(String path) throws IOException {
//        /Users/lambert/IdeaProjects/new_app/fileStorage/lambert/images/归处.png
        File file = new File(path);
        FileSystemResource fileSource = new FileSystemResource(file);
        InputStream in = fileSource.getInputStream();
        byte[] testBytes = new byte[in.available()];
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        https://www.freesion.com/article/882721051/   这个文章解决了中文乱码问题
        headers.add("Content-Disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(fileSource.getFilename(), "UTF-8"));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(testBytes.length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(in));
    }
}
