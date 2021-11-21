package com.lambert.fun.new_app.entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "t_picture")
@Table(name = "t_picture")
@Proxy(lazy = false)
public class Picture implements Serializable {
    private static final long serialVersionUID = -4689664089783752430L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
