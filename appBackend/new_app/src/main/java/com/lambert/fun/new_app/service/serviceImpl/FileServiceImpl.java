package com.lambert.fun.new_app.service.serviceImpl;

import com.lambert.fun.new_app.dao.MediaMapper;
import com.lambert.fun.new_app.dao.PictureMapper;
import com.lambert.fun.new_app.entity.Media;
import com.lambert.fun.new_app.entity.Picture;
import com.lambert.fun.new_app.entity.User;
import com.lambert.fun.new_app.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service("FileServiceImpl")
public class FileServiceImpl implements FileService {
    private Object msg = new Object();
    private Map<String, Object> map = new HashMap<>();
    private Path path = null;

    @Autowired
    MediaMapper mediaMapper;

    @Autowired
    PictureMapper pictureMapper;

    @Override

    public void init() {
        try {
//            Files.createDirectory(path); // 只创建文件
            Files.createDirectories(path); // 可以创建不存在的文件夹,已存在不会覆盖
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public Stream<Path> load() {
        try {
            return Files.walk(this.path, 1)
                    .filter(path -> !path.equals(this.path))
                    .map(this.path::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files.");
        }
    }

    @Override
    public void clear() {
        path = Paths.get("fileStorage"); // 要清空的目录地址
        FileSystemUtils.deleteRecursively(path.toFile());
    }

    @Override
    public void uploadImage(String type, MultipartFile multipartFile) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User nowUser = (User) principal;
        path = Paths.get("fileStorage/" + nowUser.getUsername() + "/images/" + type + "/"); // 创建用户头像目录
        init(); // 创建对应的目录
        try {
            String timestamp = new Date().getTime() + "_"; // 文件上传的时间戳
            String fileName = timestamp + multipartFile.getOriginalFilename().trim();
            Files.copy(multipartFile.getInputStream(), this.path.resolve(fileName)); // 将文件从缓存移至指定目录
            String path = "fileStorage/" + nowUser.getUsername() + "/images/" + type + "/" + fileName; // 文件绝对路径
            Date date = new Date();
            Picture picture = new Picture(-1L, path, date, type, nowUser);
            msg = pictureMapper.save(picture); // 返回图片id，需要后续的信息补充
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error:" + e.getMessage());
        }
    }

    @Override
    public void uploadMedia(MultipartFile multipartFile) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User nowUser = (User) principal;
        path = Paths.get("fileStorage/" + nowUser.getUsername() + "/" + "media/"); // 创建用户头像目录
        init();
        try {
            String timestamp = new Date().getTime() + "_"; // 文件上传的时间戳
            String fileName = timestamp + multipartFile.getOriginalFilename().trim();
            Files.copy(multipartFile.getInputStream(), this.path.resolve(fileName));
            String path = "fileStorage/" + nowUser.getUsername() + "/" + "media/" + fileName;
            Long likeIt = 0L;
            String type = multipartFile.getContentType();
            Long size = multipartFile.getSize();
            Integer state = 0;
            Date date = new Date();
            Media media = new Media(-1L, likeIt, path, type, size, fileName, date, state, nowUser);
            mediaMapper.save(media);
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error:" + e.getMessage());
        }
    }

    @Override
    public Object getFilePath(String type, Long id) {
        // 通过查询数据库获取path
        if (type.equals("picture")) {
            msg = pictureMapper.getById(id).getPath();
        } else if (type.equals("media")) {
            msg = mediaMapper.getById(id).getPath();
        }
        return msg;
    }

    @Override
    public Resource load(String filename) {
        Path file = path.resolve(filename);
        try {
            Resource resource = (Resource) new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file.");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error:" + e.getMessage());
        }
    }
}
