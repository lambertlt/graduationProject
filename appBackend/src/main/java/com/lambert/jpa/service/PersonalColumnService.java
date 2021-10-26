package com.lambert.jpa.service;

import com.lambert.jpa.mapper.PersonalColumnMapper;
import com.lambert.jpa.pojo.PersonalColumn;
import com.lambert.jpa.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalColumnService {
    @Autowired
    PersonalColumnMapper personalColumnMapper;
    @Autowired(required = false)
    Message message;

    public Message create(PersonalColumn personalColumn) {
        try {
            PersonalColumn personalColumn1 = personalColumnMapper.save(personalColumn);
            message = new Message(true, 200, "", personalColumn1);
        } catch (Exception e) {
            message = new Message(false, 400, "发生错误", "");
        }
        return message;
    }

    public Message delete(Long id) {
        try {
            personalColumnMapper.deleteById(id);
            message = new Message(true, 200, "删除成功", "");
        } catch (Exception e) {
            message = new Message(false, 400, "参数错误", "");
        }
        return message;
    }

    public Message update(PersonalColumn personalColumn) {
        try {
            personalColumnMapper.save(personalColumn);
            message = new Message(true, 200, "修改成功", "");
        } catch (Exception e) {
            message = new Message(false, 400, "参数错误", "");
        }
        return message;
    }

    public Message findAll() {
        try {
            List<PersonalColumn> list = personalColumnMapper.findAll();
            message = new Message(true, 200, "查找成功", list);
        } catch (Exception e) {
            message = new Message(false, 400, "", "");
        }
        return message;
    }

    public Message findAllByUserId(Long userId) {
        try {
            List<PersonalColumn> list = personalColumnMapper.findAllByUserId(userId);
            message = new Message(true, 200, "", list);
        } catch (Exception e) {
            message = new Message(false, 400, "", "");
        }
        return message;
    }

    public Message findById(Long id) {
        try {
            PersonalColumn personalColumn = personalColumnMapper.getById(id);
            message = new Message(true, 200, "", personalColumn);
        } catch (Exception e) {
            message = new Message(false, 400, "", "");
        }
        return message;
    }

    public Message findAllByClassifyId(Long classifyId) {
        try {
            List<PersonalColumn> list = personalColumnMapper.findAllByClassifyId(classifyId);
            message = new Message(true, 200, "", list);
        } catch (Exception e) {
            message = new Message(false, 400, "", "");
        }
        return message;
    }
}
