package com.lambert.jpa.mapper;

import com.lambert.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMapper extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

}
