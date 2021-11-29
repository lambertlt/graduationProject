package com.lambert.fun.new_app.dao;

import com.lambert.fun.new_app.entity.PersonalColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonalColumnMapper extends JpaRepository<PersonalColumn, Long> {
    @Query(value = "select u from t_personal_column u where u.user.id=:id")
    List<PersonalColumn> getPersonalColumnByUserId(Long id);
}
