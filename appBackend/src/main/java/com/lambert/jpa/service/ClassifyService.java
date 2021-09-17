package com.lambert.jpa.service;

import com.lambert.jpa.mapper.ClassifyMapper;
import com.lambert.jpa.model.Classify;
import com.lambert.jpa.model.User;
import com.lambert.jpa.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ClassifyService {
    @Autowired
    ClassifyMapper classifyMapper;

    public Message create(Classify classify) {
        Message message = null;
        Classify classify1 = null;
        classify1 = classifyMapper.save(classify);
        message = new Message(200, "1", classify1.toString());
        return message;
    }

    public Message delete(Long id) {
        Message message = null;
        try {
            classifyMapper.deleteById(id);
        } catch (Exception e) {
            message = new Message(400, "参数错误", "");
            return message;
        }
        message = new Message(200, "1", "".toString());
        return message;
    }

    public Message update(Classify classify) {
        Message message = null;
        Classify classify1 = null;
        try {
            classifyMapper.getById(classify.getId());
        } catch (Exception e) {
            message = new Message(400, "参数错误", "");
            return message;
        }
        try {
            classify1 = classifyMapper.save(classify);
        } catch (Exception e) {
            message = new Message(400, "参数错误", "");
            return message;
        }
        message = new Message(200, "1", classify1.toString());
        return message;
    }

    public Message findAll() {
        Message message = null;
        List<Classify> classify1 = classifyMapper.findAll();
        message = new Message(200, "1", classify1.toString());
        return message;
    }


}
