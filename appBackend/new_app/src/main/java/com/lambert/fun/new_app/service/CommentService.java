package com.lambert.fun.new_app.service;

import com.lambert.fun.new_app.entity.Comment;

public interface CommentService {
    Object getCommentById(Long id);

    Object getCommentAll();

    Object newCommentSave(Comment comment);

    Object deleteCommentById(Long id);

    Object updateComment(Comment comment);
}
