package com.lambert.jpa.mapper;

import com.lambert.jpa.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleMapper extends JpaRepository<Role, Long> {
}
