package com.lambert.fun.new_app.dao;

import com.lambert.fun.new_app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleMapper extends JpaRepository<Role, Long> {
}
