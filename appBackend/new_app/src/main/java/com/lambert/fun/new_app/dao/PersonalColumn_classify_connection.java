package com.lambert.fun.new_app.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonalColumn_classify_connection {

//    @Update("update t_user_roles set roles_id=#{rId} where t_user_id=#{uId}")
//    void changeRoles(Long uId, Long rId);
//
//    @Update("update t_user set power=#{rId} where id=#{uId}")
//    void changePower(Long uId, Long rId);

    // 用于删除中间表数据
    @Delete("delete from t_personal_column_classify where personal_columns_id=#{id}")
    void deleteByPersonalColumnId(Long id);

    @Delete("delete from t_personal_column_classify where classify_id=#{id}")
    void deleteByClassifyId(Long id);
}
