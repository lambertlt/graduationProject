package com.lambert.jpa.pojo;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Data
@Entity(name = "t_personal_column")
@Proxy(lazy = false)
// 个人专栏
public class PersonalColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id
    private String title; // 专栏名称
    private String content; // 专栏内容
    private Long userId; // 创建者id
    private String username; // 创建者的名字
    private Long classifyId; // 专栏分类

    // 创建完专栏才可以添加媒体资源
    @Transient // 设置这个之后不会将这个值存入数据库
    private long[] mediaIdArrays = {}; // 存储媒体id字符串,这个是接收到的数组
    @Transient
    private List<String> mediaArrays; // 媒体数组，存储查询好的专栏下全部媒体文件 TODO

    private String mediaIdArray; // 存储媒体id数组

    private String img; // 存放专栏图片

    void PersonalColumn() {

    }

    public void MediaIdArrayToString() {
//        this.mediaArrays = (List) this.mediaIdArrays;
        // TODO 将数组转换为集合
        this.mediaIdArray = Arrays.toString(this.mediaIdArrays); // 将发来的数组转换成字符串，
    }

    public void MediaIdArrayToArray() {
        this.mediaIdArray = this.mediaIdArray.substring(1, this.mediaIdArray.length() - 1); // 转换成"1,2,3"
        this.mediaIdArrays = StrUtil.splitToLong(this.mediaIdArray, ","); // 把字符串转换为long型数组
    }
}
