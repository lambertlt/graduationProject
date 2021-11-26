package com.lambert.fun.new_app.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
// 用于删除中间表数据
public interface ForeignDelete {
    // 通过专栏id删除 中间表数据
    @Delete("delete from t_personal_column_classify where personal_column_id=#{id}")
    void deletePersonalColumnClassifyByPersonalColumnId(Long id);

    // 通过专栏id设置 专栏表用户id外健为null
    @Update("update t_personal_column set user_id=Null where id=#{id}")
    void setPersonalColumnUserIdByPersonalColumnId(Long id);

    // 通过分类id删除 中间表数据
    @Delete("delete from t_personal_column_classify where classify_id=#{id}")
    void deletePersonalColumnClassifyByClassifyId(Long id);

    // 通过分类id设置 分类表用户id外健为null
    @Update("update t_classify set user_id=Null where id=#{id}")
    void setClassifyUserIdByClassifyId(Long id);

    // 通过用户id删除 用户权限中间表数据
    // @Delete("delete form t_picture where user_id=#{id}")
    @Delete("delete from t_user_roles where t_user_id=#{id}")
    void deleteUserRolesByUserId(Long id);

    // 通过用户id设置 照片表用户外健为null
    @Update("update t_picture set user_id=Null where user_id=#{id}")
    void setPictureUserIdByUserId(Long id);

    // 通过照片id设置 照片表分类、专栏及用户外健为null
    @Update("update t_picture set classify_id=Null,user_id=Null,personal_column_id=Null where id=#{id}")
    void setPictureClassifyIdUserIdPersonalColumnIdByPictureId(Long id);

    // 通过媒体id设置 媒体表用户id及专栏id外健为null
    @Update("update t_media set user_id=Null,personal_column_id=Null where id=#{id}")
    void setMediaUserIdPersonalColumnIdByMediaId(Long id);

    @Update("update t_media set personal_column_id=Null where personal_column_id=#{id}")
    void setMediaPersonalColumnIdByPersonalColumnId(Long id);

    @Update("update t_picture set personal_column_id=Null where personal_column_id=#{id}")
    void setPicturePersonalColumnIdByPersonalColumnId(Long id);
}
