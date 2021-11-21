package com.lambert.fun.new_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "t_media")
@Table(name = "t_media")
@Proxy(lazy = false)
public class Media implements Serializable {
    private static final long serialVersionUID = 6745246958263873196L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id
    private String title; // 媒体文件的名称
    private String img; // 媒体文件的介绍图片

    @Transient
    private Long userId; // 用户id
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"password",}) // 用于忽略某些字段
    private User user; // 上传者

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private PersonalColumn personalColumn;

    // 上传文件时同时就自动获取了
    private String path; // 媒体文件的服务器路径
    private String type; // 媒体文件的类型
    @Temporal(TemporalType.TIMESTAMP)
    private Date time; // 上传时间
    private Long size; // 大小
    private String fileName; // 文件名称
    private boolean isShow; // 媒体是否审核通过

    public Media() {

    }

    public Media(String path, String type, Date time, Long size, Long userId, String username, String fileName) {

    }

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
}
