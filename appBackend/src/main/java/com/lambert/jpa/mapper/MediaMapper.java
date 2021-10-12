package com.lambert.jpa.mapper;

import com.lambert.jpa.pojo.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface MediaMapper extends JpaRepository<Media, Long> {
    @Transactional
    @Modifying
//    @Query(value = "update t_media u set u.title=:title,u.classify=:classify where u.id=:id")
//    void update(Long id, String title, String classify);
    @Query(value = "update t_media u set u.title=:#{#media.title},u.classify=:#{#media.classify} where u.id=:#{#media.id}")
    void update(Media media);

    @Query(value = "select u from t_media u where u.userId=:id")
    List<Media> findAllByUserId(Long id);

}
