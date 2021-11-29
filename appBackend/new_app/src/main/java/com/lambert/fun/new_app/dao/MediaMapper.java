package com.lambert.fun.new_app.dao;

import com.lambert.fun.new_app.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface MediaMapper extends JpaRepository<Media, Long> {
    @Query(value = "select u.likeIt from t_media u where u.id=:id")
    Object getLikeItNumber(Long id);

    @Query(value = "select u from t_media u where u.user.id=:id")
    List<Media> getMediaUserId(Long id);

    @Transactional
    @Modifying
    @Query(value = "update t_media u set u.likeIt=:likeItNumber where u.id=:id")
    Object updateAddLikeIt(Long likeItNumber, Long id);

    @Transactional
    @Modifying
    @Query(value = "update t_media u set u.likeIt=:likeItNumber where u.id=:id")
    Object updateSubLikeIt(Long likeItNumber, Long id);
}
