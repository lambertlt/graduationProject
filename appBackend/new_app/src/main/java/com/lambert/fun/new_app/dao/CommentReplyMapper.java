package com.lambert.fun.new_app.dao;

import com.lambert.fun.new_app.entity.CommentReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReplyMapper extends JpaRepository<CommentReply, Long> {
}
