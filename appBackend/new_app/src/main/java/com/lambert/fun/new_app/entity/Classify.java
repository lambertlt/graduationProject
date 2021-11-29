package com.lambert.fun.new_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Entity(name = "t_classify")
@Table(name = "t_classify")
@Proxy(lazy = false)
public class Classify implements Serializable {
    private static final long serialVersionUID = 3959025415123330278L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 分类id
    private String name; // 分类名
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime; // 时间

    @JsonIgnoreProperties(value = {"classifySet"})
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "classifySet")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<PersonalColumn> personalColumnSet;

    @JsonIgnoreProperties(value = {"classify", "personalColumn"})
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "classify")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Picture> pictureSet;

    @JsonIgnoreProperties(value = {"password", "createTime", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "roles", "enabled", "authorities","sex","age"})
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

    public Classify() {
    }

    @Override
    public String toString() {
        personalColumnSet.forEach(data -> data.setClassifySet(null));
        pictureSet.forEach(data -> data.setClassify(null));
        pictureSet.forEach(data -> data.setPersonalColumn(null));
        return "Classify{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", personalColumnSet=" + personalColumnSet +
                ", pictureSet=" + pictureSet +
                ", user=" + user +
                '}';
    }
}
