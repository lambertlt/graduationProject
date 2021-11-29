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
@Entity(name = "t_picture")
@Table(name = "t_picture")
@Proxy(lazy = false)
public class Picture implements Serializable {
    private static final long serialVersionUID = -4689664089783752430L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path; // 绝对路径
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    private String type; // 文件类型

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = {"password", "createTime", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "roles", "enabled", "authorities","sex","age"})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long classifyId;
    @JsonIgnoreProperties(value = {"personalColumnSet", "pictureSet"})
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinColumn(name = "classify_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Classify classify;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long personalColumnId;
    @JsonIgnoreProperties(value = {"pictureSet", "classifySet"})
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinColumn(name = "personal_column_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PersonalColumn personalColumn;

    public Picture(Long id, String path, Date createTime, String type, User user) {
        this.id = id;
        this.path = path;
        this.createTime = createTime;
        this.type = type;
        this.user = user;
    }

    public Picture() {
    }

    @Override
    public String toString() {
        if (classify.getPersonalColumnSet() != null)
            classify.setPersonalColumnSet(null);
        if (classify.getPictureSet() != null)
            classify.setPictureSet(null);
        if (personalColumn.getPictureSet() != null)
            personalColumn.setPictureSet(null);
        if (personalColumn.getClassifySet() != null)
            personalColumn.setClassifySet(null);
        return "Picture{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", createTime=" + createTime +
                ", type='" + type + '\'' +
                ", user=" + user +
                ", classifyId=" + classifyId +
                ", classify=" + classify +
                ", personalColumnId=" + personalColumnId +
                ", personalColumn=" + personalColumn +
                '}';
    }
}
