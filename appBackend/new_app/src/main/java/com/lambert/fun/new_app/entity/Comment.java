package com.lambert.fun.new_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "t_comment")
@Proxy(lazy = false)
public class Comment implements Serializable {
    private static final long serialVersionUID = -5169832208487055282L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键id，为第一次评论的评论id
    private Long paternalId; // 父id
    private String commentMsg; // 评论内容
    @Temporal(TemporalType.TIMESTAMP)
    private Date creatTime;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long mediaId; // 媒体id
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "t_media_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(value = {"title", "createTime", "img", "likeIt", "path", "type", "size", "fileName", "isShow", "user", "personalColumn"})
    private Media media;

//    private Long blogId; // 主评论关联评论的主贴id即媒体id

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long replyUserId; // 用于上传用户id
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "reply_user_id")
    @JsonIgnoreProperties(value = {"password", "createTime", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "roles", "enabled"})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User replyUser; // 回复的用户信息

    // 评论人的用户信息 通过后台获取当前登陆的用户信息
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = {"password", "createTime", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "roles", "enabled"})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user; // 第一个评论用户信息

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", paternalId=" + paternalId +
                ", commentMsg='" + commentMsg + '\'' +
                ", creatTime=" + creatTime +
                ", mediaId=" + mediaId +
                ", media=" + media +
                ", replyUserId=" + replyUserId +
                ", replyUser=" + replyUser +
                ", user=" + user +
                '}';
    }
}
