package com.lambert.jpa.service;

import com.lambert.jpa.mapper.ClassifyMapper;
import com.lambert.jpa.model.Classify;
import com.lambert.jpa.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassifyService {
    @Autowired
    ClassifyMapper classifyMapper;

    public Message create(Classify classify) {
        Message message = null;
        Classify classify1 = null;
        classify1 = classifyMapper.save(classify);
        message = new Message(true, 200, "", classify1);
        return message;
    }

    public Message delete(Long id) {
        Message message = null;
        try {
            classifyMapper.deleteById(id);
        } catch (Exception e) {
            message = new Message(false, 400, "参数错误", "");
            return message;
        }
        message = new Message(true, 200, "删除成功", "");
        return message;
    }

    public Message update(Classify classify) {
        Message message = null;
        Classify classify1 = null;
        try {
            classifyMapper.getById(classify.getId());
        } catch (Exception e) {
            message = new Message(false, 400, "参数错误", "");
            return message;
        }
        try {
            classify1 = classifyMapper.save(classify);
        } catch (Exception e) {
            message = new Message(false, 400, "参数错误", "");
            return message;
        }
        message = new Message(true, 200, "", classify1);
        return message;
    }

    public Message findAll() {
        Message message = null;
        List classify1 = classifyMapper.findAll();
        message = new Message(true, 200, "", classify1);
        return message;
    }


}
