package com.lambert.jpa;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {
    public static void main(String[] args) {
        // 按指定模式在字符串查找
        String line = "FileStorage/lambert/video_20210802_165149.mp4";
        String pattern = "(?:FileStorage/.*/)(.*\\..*$)";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
//            System.out.println("Found value: " + m.group(2));
//            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }
//        System.out.println(m);
    }
}
