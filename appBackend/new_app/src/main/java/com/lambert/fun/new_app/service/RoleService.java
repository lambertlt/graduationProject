package com.lambert.fun.new_app.service;

import com.lambert.fun.new_app.entity.Role;
import org.springframework.stereotype.Component;

public interface RoleService {
    Role loadRoleById(Long id);
}
