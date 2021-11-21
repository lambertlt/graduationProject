package com.lambert.fun.new_app.dao;

import com.lambert.fun.new_app.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaMapper extends JpaRepository<Media, Long> {
}
