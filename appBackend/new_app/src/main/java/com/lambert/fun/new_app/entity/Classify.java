package com.lambert.fun.new_app.entity;

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
    private Date date; // 时间

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "uesr_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"password",}) // 用于忽略某些字段
//    @JsonInclude(JsonInclude.Include.NON_NULL) // 用于忽略为空属性
//    @JsonProperty("element_type") // 表示返回前端时，该字段命名为该值
    private User user; // 通过当前登陆的用户信息获取

    @Transient
    private List<Long> personalColumnIdList; // 用于存储专栏列表
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH}, mappedBy = "classify")
    @Fetch(FetchMode.SUBSELECT)
    private List<PersonalColumn> personalColumns; // 专栏所属分类（包括分类id、name）可以有多个

    @Override
    public String toString() {
        // 清空联级对象关于自己的调用
        personalColumns.forEach(data -> {
            data.setClassify(null);
        });
        return "Classify{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", user=" + user +
                ", personalColumnIdList=" + personalColumnIdList +
                ", personalColumns=" + personalColumns +
                '}';
    }

    public Classify(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Classify() {

    }
}
