package com.lambert.jpa.mapper;

import org.apache.ibatis.annotations.Update;

public interface UserRolesMapper {
    @Update("update t_user_roles set roles_id=#{rId} where t_user_id=#{uId}")
    void changeRoles(Long uId, Long rId);

    @Update("update t_user set power=#{rId} where id=#{uId}")
    void changePower(Long uId, Long rId);
}
