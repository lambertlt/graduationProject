package com.lambert.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        list();
    }

    static void list() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("a", 1);
        map1.put("b", 2);
        Map<String, Object> map = new HashMap<>(map1);
//        Map<String, Object> map = new HashMap<>();
//        Map<String, Object> map1 = new HashMap<String, Object>();
        map.put("b", 3);
        System.out.println(map);
    }
}
