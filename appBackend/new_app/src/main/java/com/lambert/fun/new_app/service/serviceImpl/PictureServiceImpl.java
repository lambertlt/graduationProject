package com.lambert.fun.new_app.service.serviceImpl;

import com.lambert.fun.new_app.dao.*;
import com.lambert.fun.new_app.entity.Classify;
import com.lambert.fun.new_app.entity.PersonalColumn;
import com.lambert.fun.new_app.entity.Picture;
import com.lambert.fun.new_app.entity.User;
import com.lambert.fun.new_app.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("PictureServiceImpl")
public class PictureServiceImpl implements PictureService {
    private Object msg = new Object();
    private Map<String, Object> map = new HashMap<>();

    @Autowired
    PictureMapper pictureMapper;

    @Autowired
    PersonalColumnMapper personalColumnMapper;

    @Autowired
    ClassifyMapper classifyMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ForeignDelete foreignDelete;

    public void clearMap() {
        map = new HashMap<>();
    }

    public Picture fullObject(Picture picture) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User nowUser = (User) principal;
        picture.setUser(nowUser);
        if (picture.getPersonalColumnId() != null && !picture.getPersonalColumnId().toString().trim().equals("")) {
            PersonalColumn personalColumn = personalColumnMapper.getById(picture.getPersonalColumnId());
            picture.setPersonalColumn(personalColumn);
        }
        if (picture.getClassifyId() != null && !picture.getClassifyId().toString().trim().equals("")) {
            Classify classify = classifyMapper.getById(picture.getClassifyId());
            picture.setClassify(classify);
        }
        return picture;
    }

    @Override
    public Object getPictureById(Long id) {
        msg = pictureMapper.getById(id);
        return msg;
    }

    @Override
    public Object getPictureAll() {
        msg = pictureMapper.findAll();
        return msg;
    }

    @Override
    public Object newPictureSave(Picture picture) {
        picture.setId(-1L);
        Picture newPicture = fullObject(picture);
        msg = pictureMapper.save(newPicture);
        return msg;
    }

    @Override
    public Object deletePictureById(Long id) {
        clearMap();
        foreignDelete.setPictureClassifyIdUserIdPersonalColumnIdByPictureId(id);
        pictureMapper.deleteById(id);
        map.put("id", id);
        return map;
    }

    @Override
    public Object updatePicture(Picture picture) {
        Picture newPicture = fullObject(picture);
        msg = pictureMapper.save(newPicture);
        return msg;
    }
}
