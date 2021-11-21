package com.lambert.fun.new_app.service;

import com.lambert.fun.new_app.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails loadUserByUsername(String username);

    User loadUserByUserid(Long id);

    Object newUserSave(User user);

    Object deleteUser(Long id);

    Object updateUserPassword(User user);

    Object updateUserPower(User user);
}