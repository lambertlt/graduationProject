package com.lambert.fun.new_app.service.serviceImpl;

import com.lambert.fun.new_app.entity.Media;
import com.lambert.fun.new_app.entity.User;
import com.lambert.fun.new_app.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;

@Service("FileServiceImpl")
public class FileServiceImpl implements FileService {
    private Object msg;
    private Path path;
    private Map<String, Object> map;

    // 初始化存放文件目录结构
    public void init(String username) {
        String paths = "FileStorage/" + username;
        path = Paths.get(paths);
        try {
//            Files.createDirectory(path);
            Files.createDirectories(path);
        } catch (IOException e) {
            System.out.println("Could not initialize folder for upload!");
        }
    }

    @Override
    public Object uploadMedia(MultipartFile multipartFile, User user) {
        // 上传媒体文件的处理
        String paths = "FileStorage/" + user.getUsername() + "/media/" + multipartFile.getOriginalFilename();
        File file = new File(paths);
        if (file.exists()) {
            System.out.println("file exists");

//            message = new Message(false, 400, "上传失败,文件已存在", "");
//            return message;
        }
        init(user.getUsername() + "/media/"); // 创建对应用户的个人文件夹
        try {
            Files.copy(multipartFile.getInputStream(), this.path.resolve(multipartFile.getOriginalFilename()));
//            String title; // 媒体文件的名称
            String path = paths; // 媒体文件的服务器路径
            String type = multipartFile.getContentType(); // 媒体文件的类型
            Date time = new Date(); // 上传时间
            Long size = multipartFile.getSize(); // 大小
            Long userId = user.getId(); // 用户id
            String username = user.getUsername(); // 创建者的名字
            String fileName = multipartFile.getOriginalFilename();
            Media media = new Media(path, type, time, size, userId, username, fileName);
//            Media media1 = mediaMapper.save(media);
//            message = new Message(true, 200, "上传成功", media1);
        } catch (IOException e) {
//            message = new Message(false, 400, "上传失败", "");
            System.out.println("Could not store the file. Error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public Object uploadImg() {
        return null;
    }
}
