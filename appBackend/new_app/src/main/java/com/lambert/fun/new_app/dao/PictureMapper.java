package com.lambert.fun.new_app.dao;

import com.lambert.fun.new_app.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureMapper extends JpaRepository<Picture, Long> {
}
