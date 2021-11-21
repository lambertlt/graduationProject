package com.lambert.fun.new_app.service.serviceImpl;

import com.lambert.fun.new_app.dao.RoleMapper;
import com.lambert.fun.new_app.entity.Role;
import com.lambert.fun.new_app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RoleServiceImpl")
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public Role loadRoleById(Long id) {
        Role role = roleMapper.getById(id);
        return role;
    }
}
