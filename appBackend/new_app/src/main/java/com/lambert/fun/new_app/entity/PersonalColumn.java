package com.lambert.fun.new_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
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
    private Date date; // 时间 yyyy-mm-dd:hh-mm-ss

    @Transient // 不映射该属性
    private List<Long> mediaIdList; // 关联的媒体文件列表，用于接收前台的值
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "personalColumn")
//    @JsonIgnoreProperties(value = "personalColumn")
    @Fetch(FetchMode.SUBSELECT)
    private List<Media> media; // 专栏内的音视频

    @Transient
    private List<Long> userIdList; // 关联创建者id，用于接收前台的值
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnoreProperties({"password",}) // 用于忽略某些字段
    private List<User> user; // 创建者

    @Transient
    private List<Long> classifyIdList; // 关联分类id，用于接收前台的值
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Classify> classify; // 专栏所属分类（包括分类id、name）可以有多个

    public PersonalColumn() {
    }

    @Override
    public String toString() {
        // 清空联级对象关于自己的调用
        classify.forEach(data -> {
            data.setPersonalColumns(null);
        });
        return "PersonalColumn{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", date=" + date +
                ", mediaIdList=" + mediaIdList +
                ", media=" + media +
                ", userIdList=" + userIdList +
                ", user=" + user +
                ", classifyIdList=" + classifyIdList +
                ", classify=" + classify +
                '}';
    }
}
