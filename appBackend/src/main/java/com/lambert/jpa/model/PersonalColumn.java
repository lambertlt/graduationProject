package com.lambert.jpa.model;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "t_personal_column")
@Proxy(lazy = false)
// 个人专栏
public class PersonalColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id
    private String name; // 专栏名称
    private String content; // 专栏内容
    private String media; // 存储媒体id
    private Long userId; // 创建者id
    private String username; // 创建者的名字
}
