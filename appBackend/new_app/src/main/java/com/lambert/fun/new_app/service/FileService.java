package com.lambert.fun.new_app.service;

import com.lambert.fun.new_app.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    Object uploadMedia(MultipartFile multipartFile, User user);

    Object uploadImg();
}
