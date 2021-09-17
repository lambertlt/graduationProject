package com.lambert.jpa;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class main {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("99119"));

    }
}
