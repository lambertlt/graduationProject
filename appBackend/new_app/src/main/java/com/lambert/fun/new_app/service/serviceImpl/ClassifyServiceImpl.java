package com.lambert.fun.new_app.service.serviceImpl;

import com.lambert.fun.new_app.dao.ClassifyMapper;
import com.lambert.fun.new_app.dao.ForeignDelete;
import com.lambert.fun.new_app.dao.PersonalColumnMapper;
import com.lambert.fun.new_app.entity.Classify;
import com.lambert.fun.new_app.entity.User;
import com.lambert.fun.new_app.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("ClassifyServiceImpl")
public class ClassifyServiceImpl implements ClassifyService {
    private Map<String, Object> map = new HashMap<>();

    @Autowired
    ClassifyMapper classifyMapper;

    @Autowired
    PersonalColumnMapper personalColumnMapper;

    @Autowired
    ForeignDelete foreignDelete;

    private Object msg;

    public void clearMap() {
        map = new HashMap<>();
    }

    public Classify fullObject(Classify classify) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User nowUser = (User) principal;
        classify.setUser(nowUser);
        return classify;
    }

    @Override
    public Object getClassifyById(Long id) {
        msg = classifyMapper.getById(id);
        return msg;
    }

    @Override
    public Object getClassifyAll() {
        msg = classifyMapper.findAll();
        return msg;
    }

    @Override
    public Object newClassifySave(Classify classify) {
        classify.setId(-1L);
        Classify newClassify = fullObject(classify);
        msg = classifyMapper.save(newClassify);
        return msg;
    }

    @Override
    public Object deleteClassifyById(Long id) {
        clearMap();
        try {
            foreignDelete.deletePersonalColumnClassifyByClassifyId(id);
            foreignDelete.setClassifyUserIdByClassifyId(id);
            classifyMapper.deleteById(id);
            map.put("id", id);
        } catch (Exception e) {
            map.put("error: ", e);
        }
        return map;
    }

    @Override
    public Object updateClassify(Classify classify) {
        Classify newClassify = fullObject(classify);
        msg = classifyMapper.save(newClassify);
        return msg;
    }
}
