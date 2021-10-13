package com.lambert.jpa.pojo;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "t_article")
@Proxy(lazy = false)
// 文章类
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id
    private String title; // 文章标题
    private String content; // 文章内容
    private String classify; // 文章分类

    private Long userId; // 创建者id
    private String username; // 创建者的名字

}
