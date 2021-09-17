package com.lambert.jpa.mapper;

import com.lambert.jpa.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RoleMapper extends JpaRepository<Role, Long> {
}
