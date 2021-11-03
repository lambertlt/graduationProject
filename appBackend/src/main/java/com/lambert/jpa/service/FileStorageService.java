package com.lambert.jpa.service;

import com.lambert.jpa.mapper.MediaMapper;
import com.lambert.jpa.mapper.PersonalColumnMapper;
import com.lambert.jpa.mapper.UserMapper;
import com.lambert.jpa.pojo.Media;
import com.lambert.jpa.pojo.User;
import com.lambert.jpa.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileStorageService {
    private Path path;

    @Autowired
    MediaMapper mediaMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PersonalColumnMapper personalColumnMapper;

    public Message findMediaById(Long id) {
        Message message;
        try {
            Media media = mediaMapper.getById(id);
            message = new Message(true, 200, "", media);
        } catch (Exception e) {
            message = new Message(false, 400, "参数错误", "");
        }
        return message;
    }

    public Message findAllByUserId(Long id) {
        Message message;
        try {
            List list = mediaMapper.findAllByUserId(id);
            message = new Message(true, 200, "", list);
        } catch (Exception e) {
            message = new Message(false, 400, "参数错误", "");
        }
        return message;
    }

    public Message updateMedia(Media media) {
        Message message;
        try {
//            mediaMapper.update(media.getId(), media.getTitle(), media.getClassify());
            mediaMapper.update(media);
            message = new Message(true, 200, "修改成功", "");
        } catch (Exception e) {
            message = new Message(false, 400, "参数错误", "");
        }
        return message;
    }

    public Message deleteById(Long id) {
        Message message;
        try {
            mediaMapper.deleteById(id);
            message = new Message(true, 200, "删除成功", "");
        } catch (Exception e) {
            message = new Message(false, 400, "请求参数不存在", "");
        }
        return message;
    }

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

    public Message save(MultipartFile multipartFile, User user, String types, Long id) {
        Message message = null;
        if (multipartFile.isEmpty()) {
            message = new Message(false, 400, "文件为空", "");
            return message;
        }

        if (types.equals("avatar")) {
            // 上传图片 头像
            String paths = "FileStorage/" + user.getUsername() + "/avatar/" + multipartFile.getOriginalFilename();
            File file = new File(paths);
            if (file.exists()) {
                System.out.println("file exists");
                message = new Message(false, 400, "上传失败,文件已存在", "");
                return message;
            }
            init(user.getUsername() + "/avatar/"); // 创建对应用户的个人文件夹
            try {
                Files.copy(multipartFile.getInputStream(), this.path.resolve(multipartFile.getOriginalFilename()));
                String path = paths; // 媒体文件的服务器路径
                Long userId = user.getId(); // 用户id
                Object obj = userMapper.updateAvatarPath(userId, path);
                message = new Message(true, 200, "头像上传成功", obj);
            } catch (Exception e) {
                message = new Message(false, 400, "上传失败", "");
                System.out.println("Could not store the file. Error:" + e.getMessage());
            }
        } else if (types.equals("media")) {
            // 上传图片 媒体文件图片
            String paths = "FileStorage/" + user.getUsername() + "/media/" + multipartFile.getOriginalFilename();
            File file = new File(paths);
            if (file.exists()) {
                System.out.println("file exists");
                message = new Message(false, 400, "上传失败,文件已存在", "");
                return message;
            }
            init(user.getUsername() + "/media/"); // 创建对应用户的个人文件夹
            try {
                Files.copy(multipartFile.getInputStream(), this.path.resolve(multipartFile.getOriginalFilename()));
                String path = paths; // 媒体文件的服务器路径
                Object obj = mediaMapper.updateImg(id, path);
                message = new Message(true, 200, "上传成功", obj);
            } catch (Exception e) {
                message = new Message(false, 400, "上传失败", "");
                System.out.println("Could not store the file. Error:" + e.getMessage());
            }
        } else if (types.equals("personalColumn")) {
            // 上传图片 专栏图片
            String paths = "FileStorage/" + user.getUsername() + "/personalColumn/" + multipartFile.getOriginalFilename();
            File file = new File(paths);
            if (file.exists()) {
                System.out.println("file exists");
                message = new Message(false, 400, "上传失败,文件已存在", "");
                return message;
            }
            init(user.getUsername() + "/personalColumn/"); // 创建对应用户的个人文件夹
            try {
                Files.copy(multipartFile.getInputStream(), this.path.resolve(multipartFile.getOriginalFilename()));
                String path = paths; // 媒体文件的服务器路径
                Object obj = personalColumnMapper.updateImg(id, path);
                message = new Message(true, 200, "上传成功", obj);
            } catch (Exception e) {
                message = new Message(false, 400, "上传失败", "");
                System.out.println("Could not store the file. Error:" + e.getMessage());
            }
        } else if (types.equals("file")) {
            // 上传媒体文件的处理
            String paths = "FileStorage/" + user.getUsername() + "/media/" + multipartFile.getOriginalFilename();
            File file = new File(paths);
            if (file.exists()) {
                System.out.println("file exists");
                message = new Message(false, 400, "上传失败,文件已存在", "");
                return message;
            }
            init(user.getUsername() + "/media/"); // 创建对应用户的个人文件夹
            try {
                Files.copy(multipartFile.getInputStream(), this.path.resolve(multipartFile.getOriginalFilename()));
//            String title; // 媒体文件的名称
                String path = paths; // 媒体文件的服务器路径
                String type = multipartFile.getContentType(); // 媒体文件的类型
                Long time = new Date().getTime(); // 上传时间
                Long size = multipartFile.getSize(); // 大小
                Long userId = user.getId(); // 用户id
                String username = user.getUsername(); // 创建者的名字
                String fileName = multipartFile.getOriginalFilename();
                Media media = new Media(path, type, time, size, userId, username, fileName);
                Media media1 = mediaMapper.save(media);
                message = new Message(true, 200, "上传成功", media1);
            } catch (IOException e) {
                message = new Message(false, 400, "上传失败", "");
                System.out.println("Could not store the file. Error:" + e.getMessage());
            }
        }
        return message;
    }

    public Message download(String fileNames, String path, HttpServletResponse response) {
        // 加载文件提供下载服务
        Message message = null;
//        Path file = Paths.get(path);
        if (path != null) {
            //设置文件路径
            File file = new File(path);
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileNames);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    message = new Message(true, 200, "下载成功", "");
                    return message;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        message = new Message(false, 400, "下载失败", "");
        return message;
    }

    public Stream<Path> load() {
        try {
            return Files.walk(this.path, 1)
                    .filter(path -> !path.equals(this.path))
                    .map(this.path::relativize);
        } catch (IOException e) {
            System.out.println("Could not load the files.");
        }
        return null;
    }

    public void clear() {
        FileSystemUtils.deleteRecursively(path.toFile());
    }

}
