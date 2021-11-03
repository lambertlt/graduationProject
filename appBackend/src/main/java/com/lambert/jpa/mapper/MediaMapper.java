package com.lambert.jpa.mapper;

import com.lambert.jpa.pojo.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface MediaMapper extends JpaRepository<Media, Long> {
    @Transactional
    @Modifying
//    @Query(value = "update t_media u set u.title=:title,u.classify=:classify where u.id=:id")
//    void update(Long id, String title, String classify);
    @Query(value = "update t_media u set u.title=:#{#media.title},u.classifyId=:#{#media.classifyId} where u.id=:#{#media.id}")
    void update(Media media);

    @Query(value = "select u from t_media u where u.userId=:id")
    List<Media> findAllByUserId(Long id);

    @Transactional
    @Modifying
    @Query(value = "update t_media u set u.img=:path where u.id=:id")
    Object updateImg(Long id, String path);

    @Query(nativeQuery = true, value = "select * from t_media where id in (:list)")
    List<Map<String, Object>> getMediaByArrayString(List<String> list);

//    @Query(nativeQuery = true, value = "select * from t_media where id in (:list)")
//    Map<String, Object> getMediaByArray(String[] list);

    @Query(nativeQuery = true, value = "select * from t_media where id in (:list)")
    List<Map<String, Object>> getMediaByArrayLong(long[] list);
    // jpa dao层默认返回List<Map<String,Object> 格式的数据
}
