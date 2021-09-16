package com.lambert.app.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nameZh;
}
