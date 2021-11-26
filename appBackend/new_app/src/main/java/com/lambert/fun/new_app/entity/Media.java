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
    @Temporal(TemporalType.TIMESTAMP)
    private Date creatTime; // 上传时间

    private Long likeIt; // 点赞，初始值为0

//    todo 设计实现一个完备的用户点赞历史列表
//    @Transient
//    private List<Long> likeItUserIdList; // 当前登陆的用户，点赞的用户id 列表

    // 上传文件时同时就自动获取了
    private String path; // 媒体文件的服务器路径
    private String type; // 媒体文件的类型
    private Long size; // 大小
    private String fileName; // 文件名称
    private boolean isShow; // 媒体是否审核通过

    @JsonIgnoreProperties(value = {"password", "createTime", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "roles", "enabled", "authorities"})
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "personal_column_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PersonalColumn personalColumn;

    public Media(Long id, Long likeIt, String path, String type, Long size, String fileName, Date creatTime, boolean isShow) {
        this.id = id;
        this.likeIt = likeIt;
        this.path = path;
        this.type = type;
        this.size = size;
        this.fileName = fileName;
        this.isShow = isShow;
        this.creatTime = creatTime;
    }

    public Media() {

    }


    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", creatTime=" + creatTime +
                ", likeIt=" + likeIt +
                ", path='" + path + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", fileName='" + fileName + '\'' +
                ", isShow=" + isShow +
                ", user=" + user +
                ", personalColumn=" + personalColumn +
                '}';
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
