package com.lambert.fun.new_app.service.serviceImpl;

import com.lambert.fun.new_app.dao.*;
import com.lambert.fun.new_app.entity.Classify;
import com.lambert.fun.new_app.entity.PersonalColumn;
import com.lambert.fun.new_app.entity.User;
import com.lambert.fun.new_app.service.PersonalColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("PersonalColumnServiceImpl")
public class PersonalColumnServiceImpl implements PersonalColumnService {
    private Map<String, Object> map = new HashMap<>();
    private Object msg = new Object();

    @Autowired
    PersonalColumnMapper personalColumnMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ClassifyMapper classifyMapper;

    @Autowired
    MediaMapper mediaMapper;

    @Autowired
    ForeignDelete foreignDelete;

    public void clearMap() {
        map = new HashMap<>();
    }

    // 该方法用于填充满对象内的属性
    public PersonalColumn fullObject(PersonalColumn personalColumn) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User nowUser = (User) principal;
        personalColumn.setUser(nowUser);
//        if (!personalColumn.getUserIdList().isEmpty() && personalColumn.getUserIdList() != null) {
//            personalColumn.setUser(userMapper.findAllById(personalColumn.getUserIdList()));
//        }
//        if (!personalColumn.getMediaIdList().isEmpty() && personalColumn.getMediaIdList() != null) {
//            personalColumn.setMedia(mediaMapper.findAllById(personalColumn.getMediaIdList()));
//        }
        if (!personalColumn.getClassifyIdList().isEmpty() && personalColumn.getClassifyIdList() != null) {
            personalColumn.setClassifySet(new HashSet(classifyMapper.findAllById(personalColumn.getClassifyIdList())));
        }
        return personalColumn;
    }

    @Override
    public Object getPersonalColumnById(Long id) {
        msg = personalColumnMapper.getById(id);
        return msg;
    }

    @Override
    public Object getPersonalColumnAll() {
        msg = personalColumnMapper.findAll();
        return msg;
    }

    @Override
    public Object newPersonalColumnSave(PersonalColumn personalColumn) {
        personalColumn.setId(-1L);
        PersonalColumn newPersonalColumn = fullObject(personalColumn);
        msg = personalColumnMapper.save(newPersonalColumn);
        return msg;
    }

    @Override
    public Object deletePersonalColumnById(Long id) {
        clearMap();
        try {
            foreignDelete.deletePersonalColumnClassifyByPersonalColumnId(id);
            foreignDelete.setPersonalColumnUserIdByPersonalColumnId(id);
            foreignDelete.setMediaPersonalColumnIdByPersonalColumnId(id);
            foreignDelete.setPicturePersonalColumnIdByPersonalColumnId(id);
            personalColumnMapper.deleteById(id);
            map.put("id", id);
        } catch (Exception e) {
            map.put("error: ", e);
        }
        return map;
    }

    @Override
    public Object getPersonalColumnByUserId(Long id) {
        msg = personalColumnMapper.getPersonalColumnByUserId(id);
        return msg;
    }

    @Override
    public Object updatePersonalColumn(PersonalColumn personalColumn) {
        PersonalColumn newPersonalColumn = fullObject(personalColumn);
        msg = personalColumnMapper.save(newPersonalColumn);
        return msg;
    }
}
