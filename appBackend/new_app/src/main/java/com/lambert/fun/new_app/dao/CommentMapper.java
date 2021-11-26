package com.lambert.fun.new_app.dao;

import com.lambert.fun.new_app.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentMapper extends JpaRepository<Comment, Long> {
}
