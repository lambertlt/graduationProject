package com.lambert.jpa.controller;

import com.lambert.jpa.mapper.PersonalColumnMapper;
import com.lambert.jpa.model.PersonalColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personalColumn")
public class PersonalColumnController {
    @Autowired
    PersonalColumnMapper personalColumnMapper;

    /**
     * showdoc
     *
     * @param "title"        必选 String 专栏标题
     * @param "content"      必选 String 专栏内容
     * @param "mediaIdArray" 必选 Array 专栏内音视频id数组
     * @param "userId"       必选 Long 用户Id
     * @param "username"     必选 String 用户名
     * @return {"status":200,"data":{"id":"1","username":"12154545"}}
     * @catalog 个人专栏接口
     * @title 专栏创建
     * @description 访问权限：用户；
     * @method post
     * @url /personalColumn/create
     * @header token 可选 string 设备token
     * @return_param status int 状态码
     * @return_param data String 数据
     * @return_param success Boolean 是否成功
     * @return_param message String 消息
     * @remark 现在还不能用
     * @number null
     */
    @PostMapping("/create")
    void create(PersonalColumn personalColumn, Long[] mediaIdArrays) {
        String mediaIdArray = mediaIdArrays.toString();
        personalColumnMapper.save(personalColumn);
    }

    @GetMapping("/delete")
    void delete(@RequestParam(name = "id") Long id) {
        personalColumnMapper.deleteById(id);
    }

    @PostMapping("/update")
    void update(PersonalColumn personalColumn) {
        personalColumnMapper.save(personalColumn);
    }

    @GetMapping("/findAll")
    void findAll() {
        personalColumnMapper.findAll();
    }

    @GetMapping("/findAllByUserId")
    void findAllByUserId() {
    }

    @GetMapping("/findById")
    void findById(@RequestParam(name = "id") Long id) {
        personalColumnMapper.getById(id);
    }
}
