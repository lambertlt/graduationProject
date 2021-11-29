package com.lambert.fun.new_app.dao;

import com.lambert.fun.new_app.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PictureMapper extends JpaRepository<Picture, Long> {
    @Query(value = "select u from t_picture u where u.user.id=:userId")
    List<Picture> getPictureByUserId(Long userId);

    @Query(value = "select u from t_picture u where u.type=:type")
    List<Picture> getPictureByType(String type);

    @Query(value = "select u from t_picture u where u.type=:type and u.user.id=:userId")
    List<Picture> getPictureByTypeAndUserId(String type, Long userId);
}
