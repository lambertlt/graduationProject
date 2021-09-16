package com.lambert.app.mapper;

import com.lambert.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMapper extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    Optional<User> findById(Long id);
}

