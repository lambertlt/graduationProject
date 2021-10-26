package com.lambert.jpa.pojo;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Data
@Entity(name = "t_media")
@Proxy(lazy = false)
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id
    private String title; // 媒体文件的名称
    private Long classifyId; // 所属的大分类
    private String img; // 媒体文件的介绍图片

    // 上传文件时同时就自动获取了
    private String path; // 媒体文件的服务器路径
    private String type; // 媒体文件的类型
    private Long time; // 上传时间
    private Long size; // 大小
    private Long userId; // 创建者的id
    private String username; // 创建者的名字
    private String fileName; // 文件名称
    private boolean isShow; // 媒体是否审核通过
//                // 获取文件后缀
//                String line = path;
//                String pattern = "(?:FileStorage/.*/)(.*\\..*$)";
//                // 创建 Pattern 对象
//                Pattern r = Pattern.compile(pattern);
//                // 现在创建 matcher 对象
//                Matcher m = r.matcher(line);
//                String fileNames = new String();
//                if (m.find()) {
//                    fileNames = m.group(1);
//                }

    public Media(String path, String type, Long time, Long size, Long userId, String username, String fileName) {
        this.path = path;
        this.type = type;
        this.time = time;
        this.size = size;
        this.userId = userId;
        this.username = username;
        this.fileName = fileName;
        this.isShow = false;
    }

    public Media(String title, String path, String type, Long time, Long size, Long classifyId, Long userId, String username) {
        this.title = title;
        this.path = path;
        this.type = type;
        this.time = time;
        this.size = size;
        this.classifyId = classifyId;
        this.userId = userId;
        this.username = username;
        this.isShow = false;
    }

    public Media() {

    }
}
