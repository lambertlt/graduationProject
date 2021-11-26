package com.lambert.fun.new_app.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "t_role")
@Table(name = "t_role")
@Proxy(lazy = false)
public class Role implements Serializable {
    private static final long serialVersionUID = 8288904189979200360L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*
     * TABLE：使用一个特定的数据库表格来保存主键。
     * SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
     * IDENTITY：主键由数据库自动生成（主要是自动增长型）
     * AUTO：主键由程序控制。
     * */
    private Long id;
    private String name;
    private String nameZh;

    public static Role getAdmin() {
        Role adminRole = new Role(1L, "ROLE_admin", "管理员");
        return adminRole;
    }

    public static Role getMedia() {
        Role mediaRole = new Role(2L, "ROLE_media", "审核员");
        return mediaRole;
    }

    public static Role getUser() {
        Role userRole = new Role(3L, "ROLE_user", "普通用户");
        return userRole;
    }

    public static Role getAdminInit() {
        Role adminRole = new Role(-1L, "ROLE_admin", "管理员");
        return adminRole;
    }

    public static Role getUserInit() {
        Role userRole = new Role(-1L, "ROLE_user", "普通用户");
        return userRole;
    }

    public static Role getMediaInit() {
        Role mediaRole = new Role(-1L, "ROLE_media", "审核员");
        return mediaRole;
    }

    public Role() {
    }

    public Role(Long id, String name, String nameZh) {
        this.id = id;
        this.name = name;
        this.nameZh = nameZh;
    }

    /**
     * 让这个实体维护关系
     * JoinColumn 表示声明外键
     * name                    当前表中的外键名
     * referencedColumnName    指向的对方表中的主键名
     */

//    @OneToOne
//    @JoinColumn(name = "articleId", referencedColumnName = "aid", unique = true)
}
