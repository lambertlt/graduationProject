package com.lambert.fun.new_app.service;

import com.lambert.fun.new_app.entity.CommentReply;

public interface CommentReplyService {
    Object getCommentReplyById(Long id);

    Object getCommentReplyAll();

    Object newCommentReplySave(CommentReply commentReply);

    Object deleteCommentReplyById(Long id);

    Object updateCommentReply(CommentReply commentReply);
}
