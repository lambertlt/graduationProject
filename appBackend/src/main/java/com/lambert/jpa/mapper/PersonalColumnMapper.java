package com.lambert.jpa.mapper;

import com.lambert.jpa.pojo.PersonalColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonalColumnMapper extends JpaRepository<PersonalColumn, Long> {
    @Query(value = "select t from t_personal_column t where t.userId = :userId")
    List<PersonalColumn> findAllByUserId(Long userId);

    @Query(value = "select t from t_personal_column t where t.classifyId = :classifyId")
    List<PersonalColumn> findAllByClassifyId(Long classifyId);
}
