package com.lambert.fun.new_app.service;

import com.lambert.fun.new_app.entity.Picture;

public interface PictureService {
    Object getPictureById(Long id);

    Object getPictureByUserId(Long id);

    Object getPictureByType(String type);

    Object getPictureByTypeAndUserId(String type, Long userId);

    Object getPictureAll();

    Object newPictureSave(Picture picture);

    Object deletePictureById(Long id);

    Object updatePicture(Picture picture);
}
