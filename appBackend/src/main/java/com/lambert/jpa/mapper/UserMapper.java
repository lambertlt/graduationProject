package com.lambert.jpa.mapper;

import com.lambert.jpa.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface UserMapper extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    @Transactional
    @Modifying
    // 方法一
//    @Query(value = "update t_user u set u.password=:encode where u.id=:id")
//    void changePassword(Long id, String encode);

    // 方法二
//    @Query(value = "update t_user u set u.password=?2 where u.id=?1")
//    void changePassword(Long id, String encode);

    // 方法三
    @Query(value = "update t_user u set u.password=:encode where u.id=:id")
    void changePassword(@Param("id") Long id, @Param("encode") String encode);

    // 更新用户头像地址
    @Transactional
    @Modifying
    @Query(value = "update t_user u set u.avatar=:path where u.id=:id")
    Object updateAvatarPath(@Param("id") Long id, @Param("path") String path);
}
