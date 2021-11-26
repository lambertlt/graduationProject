package com.lambert.fun.new_app.service;

import com.lambert.fun.new_app.entity.Media;

public interface MediaService {
    Object getMediaById(Long id);

    Object getMediaAll();

    Object getLikeIt(Long id);

    Object updateAddLikeIt(Long id);

    Object updateSubLikeIt(Long id);

    Object newMediaSave(Media media);

    Object deleteMediaById(Long id);

    Object updateMedia(Media media);
}
