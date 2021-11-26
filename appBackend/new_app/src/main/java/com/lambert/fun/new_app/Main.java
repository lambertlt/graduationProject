package com.lambert.fun.new_app;

import com.lambert.fun.new_app.entity.Picture;
import org.springframework.security.core.parameters.P;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        Picture picture = new Picture();
//        picture.setClassifyId(16L);
//        System.out.println(picture.getClassifyId().toString().trim());
//        System.out.println(picture.getClassifyId() != null && !picture.getClassifyId().toString().trim().equals(""));
//        System.out.println(!picture.getClassifyId().toString().trim().equals(""));

    }

    static void a() {
        System.out.println(new Date());
        System.out.println(System.currentTimeMillis());
        Map map = new HashMap<>();
        map.put(1, null);
        map.put(2, "value 2");
        map.put(3, "value 3");
        System.out.println(map);
        map.values().removeIf(value -> value == null);
        System.out.println(map);
    }
}
