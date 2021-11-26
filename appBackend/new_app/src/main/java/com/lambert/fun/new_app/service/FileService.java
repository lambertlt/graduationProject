package com.lambert.fun.new_app.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileService {
    void init();

    void uploadImage(String type, MultipartFile multipartFile);

    void uploadMedia(MultipartFile multipartFile);

    Object getFilePath(String type, Long id);

    Resource load(String filename);

    Stream<Path> load();

    void clear();

}
