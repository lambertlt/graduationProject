package com.lambert.jpa.controller;

import com.lambert.jpa.model.Classify;
import com.lambert.jpa.service.ClassifyService;
import com.lambert.jpa.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestController
public class ClassifyController {
    @Autowired
    ClassifyService classifyService;

    /**
     * showdoc
     *
     * @param "name" 必选 String 标签名
     * @return {"data":{"id":10,"name":"jjjj"},"success":true,"message":"","status":200}
     * @catalog 分类接口
     * @title 创建分类
     * @description 访问权限：管理员；创建一个分类
     * @method post
     * @url /classify/create
     * @return_param status int 状态码
     * @return_param data String 数据
     * @remark null
     * @number null
     */
    @PostMapping("/classify/create")
    public void create(@RequestBody Classify classify, HttpServletResponse resp) throws IOException {
        Message message = classifyService.create(classify);
        message.returnJson(resp);
    }

    /**
     * showdoc
     *
     * @param "id" 必选 Long 标签id
     * @return {"data":"","success":true,"message":"删除成功","status":200}
     * @catalog 分类接口
     * @title 删除分类
     * @description 访问权限：管理员；删除一个分类
     * @method get
     * @return_param status int 状态码
     * @return_param data String 数据
     * @url /classify/delete
     * @remark null
     * @number null
     */
    @GetMapping("/classify/delete")
    public void delete(@RequestParam(name = "id") Long id, HttpServletResponse resp) throws IOException {
        Message message = classifyService.delete(id);
        message.returnJson(resp);
    }

    /**
     * showdoc
     *
     * @param "id"   必选 Long 标签id
     * @param "name" 必选 String 标签名
     * @return {"data":{"id":1,"name":"幼儿"},"success":true,"message":"","status":200}
     * @catalog 分类接口
     * @title 修改分类
     * @description 访问权限：管理员；修改一个分类
     * @method post
     * @url /classify/update
     * @return_param status int 状态码
     * @return_param data String 数据
     * @remark
     * @number null
     */
    @PostMapping("/classify/update")
    public void update(@RequestBody Classify classify, HttpServletResponse resp) throws IOException {
        Message message = classifyService.update(classify);
        message.returnJson(resp);
    }

    /**
     * showdoc
     *
     * @return {"data":[{"id":1,"name":"幼000儿"},{"id":5,"name":"jjjj"}],"success":true,"message":"","status":200}
     * @catalog 分类接口
     * @title 查找全部分类
     * @description 访问权限：任意；查找全部分类
     * @method get
     * @url /classify/findAll
     * @return_param status int 状态码
     * @return_param data String 数据
     * @remark null
     * @number null
     */
    @GetMapping("/classify/findAll")
    public void findAll(HttpServletResponse resp) throws IOException {
        Message message = classifyService.findAll();
        message.returnJson(resp);
    }
}
