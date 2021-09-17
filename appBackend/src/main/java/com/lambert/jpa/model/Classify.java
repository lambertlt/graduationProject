package com.lambert.jpa.model;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "t_classify")
@Proxy(lazy = false)
public class Classify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id
    private String name; // 分类名称

    public Classify(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Classify() {

    }
}