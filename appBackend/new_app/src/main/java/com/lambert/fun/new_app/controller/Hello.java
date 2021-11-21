package com.lambert.fun.new_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("home")
public class Hello {
    @GetMapping("hello/{id}/name/{name}") // 获取单个参数时十分方便
    public String hello(@PathVariable(name = "id") Long ids, @PathVariable(name = "name") String name) {
        return "hello " + ids + name;
    }

    @GetMapping("hi")
    public String hi(@RequestParam(name = "id", required = false) Long id) {
        return "hi " + id;
    }

    @GetMapping("hey/name/{name}")
    ResponseEntity<Map> hey(@PathVariable(name = "name") String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", name);
        return ResponseEntity.ok(map);
    }
}
