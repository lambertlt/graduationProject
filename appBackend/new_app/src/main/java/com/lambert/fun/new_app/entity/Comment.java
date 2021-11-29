package com.lambert.fun.new_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "t_comment")
@Proxy(lazy = false)
// 评论表
public class Comment implements Serializable {
    private static final long serialVersionUID = -5169832208487055282L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键id，为第一次评论的评论id
    private String commentMsg; // 评论内容
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime; // 第一个评论时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime; // 最近评论时间
    private Integer state = CommentState.OK.getNum(); // 审核状态 通过枚举类型设置, 默认为通过状态
    private Long likeIt; // 评论点赞数

    @JsonIgnoreProperties("comment")
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "comment")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<CommentReply> commentReplySet;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long mediaId; // 媒体id
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "t_media_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(value = {"title", "createTime", "img", "likeIt", "path", "type", "size", "fileName", "isShow", "user", "personalColumn", "state"})
    private Media media;

    // 评论人的用户信息 通过后台获取当前登陆的用户信息
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = {"password", "createTime", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "roles", "enabled", "authorities", "sex", "age"})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user; // 发表评论用户信息

    @Override
    public String toString() {
        commentReplySet.forEach(data -> data.setComment(null));
        return "Comment{" +
                "id=" + id +
                ", commentMsg='" + commentMsg + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", state=" + state +
                ", likeIt=" + likeIt +
                ", commentReplySet=" + commentReplySet +
                ", mediaId=" + mediaId +
                ", media=" + media +
                ", user=" + user +
                '}';
    }

    enum CommentState {
        TODO(0, "待审核"),
        OK(1, "通过"),
        NO(3, "未通过");

        private Integer num;
        private String msg;

        CommentState(Integer num, String msg) {
            this.num = num;
            this.msg = msg;
        }

        public String getMsg() {
            return this.msg;
        }

        public Integer getNum() {
            return this.num;
        }
    }
}
