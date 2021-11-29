package com.lambert.fun.new_app.service.serviceImpl;

import com.lambert.fun.new_app.dao.CommentMapper;
import com.lambert.fun.new_app.dao.CommentReplyMapper;
import com.lambert.fun.new_app.dao.ForeignDelete;
import com.lambert.fun.new_app.dao.UserMapper;
import com.lambert.fun.new_app.entity.Comment;
import com.lambert.fun.new_app.entity.CommentReply;
import com.lambert.fun.new_app.entity.User;
import com.lambert.fun.new_app.service.CommentReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("CommentReplyServiceImpl")
public class CommentReplyServiceImpl implements CommentReplyService {
    private Object msg = new Object();
    private Map map = new HashMap();

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommentReplyMapper commentReplyMapper;

    @Autowired
    ForeignDelete foreignDelete;

    public void clearMap() {
        map = new HashMap();
    }

    public CommentReply fullObject(CommentReply commentReply) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User nowUser = (User) principal;
        commentReply.setUser(nowUser);
        if (commentReply.getCommentId() != null && !commentReply.getCommentId().toString().trim().equals("")) {
            Comment comment = commentMapper.getById(commentReply.getCommentId());
            commentReply.setComment(comment);
        }
        if (commentReply.getReplyUserId() != null && !commentReply.getReplyUserId().toString().trim().equals("")) {
            User replyUser = userMapper.getById(commentReply.getReplyUserId());
            commentReply.setReplyUser(replyUser);
        }
        return commentReply;
    }

    @Override
    public Object getCommentReplyById(Long id) {
        return null;
    }

    @Override
    public Object getCommentReplyAll() {
        return null;
    }

    @Override
    public Object newCommentReplySave(CommentReply commentReply) {
        commentReply.setId(-1L);
        commentReply.setLikeIt(0L);
        CommentReply newCommentReply = fullObject(commentReply);
        msg = commentReplyMapper.save(newCommentReply);
        return msg;
    }

    @Override
    public Object deleteCommentReplyById(Long id) {
        clearMap();
        foreignDelete.setCommentReplyCommentIdReplyUserIdUserIdByCommentReplyId(id);
        commentReplyMapper.deleteById(id);
        map.put("id", id);
        return map;
    }

    @Override
    public Object updateCommentReply(CommentReply commentReply) {
        return null;
    }
}
