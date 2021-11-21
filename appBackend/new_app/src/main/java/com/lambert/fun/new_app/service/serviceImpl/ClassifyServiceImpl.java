package com.lambert.fun.new_app.service.serviceImpl;

import com.lambert.fun.new_app.dao.ClassifyMapper;
import com.lambert.fun.new_app.dao.PersonalColumnMapper;
import com.lambert.fun.new_app.entity.Classify;
import com.lambert.fun.new_app.entity.PersonalColumn;
import com.lambert.fun.new_app.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ClassifyServiceImpl")
public class ClassifyServiceImpl implements ClassifyService {
    private Map<String, Object> map = new HashMap<>();

    @Autowired
    ClassifyMapper classifyMapper;

    @Autowired
    PersonalColumnMapper personalColumnMapper;

    private Object msg;
    private List<PersonalColumn> personalColumnList = null;

    public Classify fullObject(Classify classify) {
        if (classify.getPersonalColumnIdList() != null && !classify.getPersonalColumnIdList().isEmpty()) {
            personalColumnList = new ArrayList<>(personalColumnMapper.findAllById(classify.getPersonalColumnIdList()));
            classify.setPersonalColumns(personalColumnList);
        }
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
        try {
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
