package com.lambert.fun.new_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "t_personal_column")
@Table(name = "t_personal_column")
@Proxy(lazy = false)
// 个人专栏
public class PersonalColumn implements Serializable {
    private static final long serialVersionUID = 282071625684778330L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id
    private String title; // 专栏名称
    private String content; // 专栏内容
    private String img; // 专栏封面图 path
    @Temporal(TemporalType.TIMESTAMP)
    private Date creatTime; // 时间 yyyy-mm-dd:hh-mm-ss

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Long> classifyIdList;
    @JsonIgnoreProperties(value = {"personalColumnSet"})
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinTable(name = "t_personal_column_classify", joinColumns = @JoinColumn(name = "personal_column_id"), inverseJoinColumns = @JoinColumn(name = "classify_id"))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Classify> classifySet;

    @JsonIgnoreProperties(value = {"password", "createTime", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "roles", "enabled", "authorities"})
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

    @JsonIgnoreProperties(value = {"personalColumn", "classify"})
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "personalColumn")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Picture> pictureSet;

    @JsonIgnoreProperties(value = {"personalColumn"})
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "personalColumn")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    // todo error: java.lang.ClassCastException: class java.util.ArrayList cannot be cast to class java.util.Set (java.util.ArrayList and java.util.Set are in module java.base of loader 'bootstrap')
    private Set<Media> mediaSet;

    public PersonalColumn() {
    }

    @Override
    public String toString() {
        mediaSet.forEach(data -> data.setPersonalColumn(null));
        pictureSet.forEach((data -> data.setPersonalColumn(null)));
        pictureSet.forEach((data -> data.setClassify(null)));
        classifySet.forEach(data -> data.setPersonalColumnSet(null));
        return "PersonalColumn{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", creatTime=" + creatTime +
                ", classifyIdList=" + classifyIdList +
                ", classifySet=" + classifySet +
                ", user=" + user +
                ", pictureSet=" + pictureSet +
                ", mediaSet=" + mediaSet +
                '}';
    }
}
