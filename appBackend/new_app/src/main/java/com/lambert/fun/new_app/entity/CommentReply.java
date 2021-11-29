package com.lambert.fun.new_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "t_comment_reply")
@Proxy(lazy = false)
// 评论回复表
public class CommentReply implements Serializable {
    private static final long serialVersionUID = 4939473555658029442L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 评论id
    private String commentMsg; // 评论内容
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    private Long likeIt; //点赞数 初始值为 0

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long commentId; // 主评论id，即第一层
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinColumn(name = "comment_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Comment comment; // 关联主评论

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long replyUserId; // 用于上传用户id
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "reply_user_id")
    @JsonIgnoreProperties(value = {"password", "createTime", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "roles", "enabled", "authorities", "sex", "age"})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User replyUser; // 回复的用户信息

    // 评论人的用户信息 通过后台获取当前登陆的用户信息
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = {"password", "createTime", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "roles", "enabled", "authorities", "sex", "age"})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user; // 发表评论用户信息

    @Override
    public String toString() {
        if (comment.getCommentReplySet() != null)
            comment.setCommentReplySet(null);
        return "CommentReply{" +
                "id=" + id +
                ", commentMsg='" + commentMsg + '\'' +
                ", createTime=" + createTime +
                ", likeIt=" + likeIt +
                ", commentId=" + commentId +
                ", comment=" + comment +
                ", replyUserId=" + replyUserId +
                ", replyUser=" + replyUser +
                ", user=" + user +
                '}';
    }
}
