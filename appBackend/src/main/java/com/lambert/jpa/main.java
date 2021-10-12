package com.lambert.jpa;

import cn.hutool.core.util.StrUtil;
import org.apache.ibatis.reflection.ArrayUtil;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {
    public static void main(String[] args) {
        Long[] arr = {1L, 2L};
//        String[] arrs= {"1", "2"};
//        List<Long> list = Arrays.asList(arr);
//        String str4 = StringUtils.join(list, ',');
//        String str1 = ArrayUtil.join(arr, ",");
//        System.out.println(str4);
        String arrs = "1, 2";

//        System.out.println(arrs.substring(1, arrs.length() - 1));
//        String newArr = Arrays.toString(arr);
        String[] a = arrs.split(",");
        long[] longs = StrUtil.splitToLong(arrs, ",");
        for (long i : longs) {
            System.out.println(i);
        }
//        System.out.println(a);
    }

    public void zz() {
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
