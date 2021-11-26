package com.lambert.fun.new_app.service.serviceImpl;

import com.lambert.fun.new_app.dao.CommentMapper;
import com.lambert.fun.new_app.dao.MediaMapper;
import com.lambert.fun.new_app.dao.UserMapper;
import com.lambert.fun.new_app.entity.Comment;
import com.lambert.fun.new_app.entity.User;
import com.lambert.fun.new_app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("CommentServiceImpl")
public class CommentServiceImpl implements CommentService {
    private Object msg = new Object();
    private Map map = new HashMap();

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    MediaMapper mediaMapper;

    public void clearMap() {
        map = new HashMap();
    }

    public Comment fullObject(Comment comment) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User nowUser = (User) principal;
        comment.setUser(nowUser);
        if (comment.getReplyUserId() != null && !comment.getReplyUserId().toString().trim().equals("")) {
            comment.setReplyUser(userMapper.getById(comment.getReplyUserId()));
        }
        if (comment.getMediaId() != null && !comment.getMediaId().toString().trim().equals("")) {
            comment.setMedia(mediaMapper.getById(comment.getMediaId()));
        }
        return comment;
    }


    @Override
    public Object getCommentById(Long id) {
        return null;
    }

    @Override
    public Object getCommentAll() {
        return null;
    }

    @Override
    public Object newCommentSave(Comment comment) {
        comment.setId(-1L);
        Comment newComment = fullObject(comment);
        msg = commentMapper.save(newComment);
        return msg;
    }

    @Override
    public Object deleteCommentById(Long id) {
        return null;
    }

    @Override
    public Object updateComment(Comment comment) {
        return null;
    }
}
