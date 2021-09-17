package com.lambert.jpa.model;


import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "t_media")
@Proxy(lazy = false)
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id
    private String title; // 媒体文件的名称
    private String path; // 媒体文件的服务器路径
    private String type; // 媒体文件的类型
    private String time; // 上传时间
    private Float size; // 大小
    private String classify; // 所属的大分类
    private Long userId; // 创建者的id
    private String username; // 创建者的名字

}
