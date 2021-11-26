package com.lambert.fun.new_app.service.serviceImpl;

import com.lambert.fun.new_app.dao.ForeignDelete;
import com.lambert.fun.new_app.dao.MediaMapper;
import com.lambert.fun.new_app.entity.Media;
import com.lambert.fun.new_app.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("MediaServiceImpl")
public class MediaServiceImpl implements MediaService {
    private Object msg = new Object();
    private Map<String, Object> map = new HashMap<>();

    @Autowired
    MediaMapper mediaMapper;

    @Autowired
    ForeignDelete foreignDelete;

    public void clearMap() {
        map = new HashMap<>();
    }

    @Override
    public Object getMediaById(Long id) {
        msg = mediaMapper.getById(id);
        return msg;
    }

    @Override
    public Object getMediaAll() {
        msg = mediaMapper.findAll();
        return msg;
    }

    @Override
    public Object getLikeIt(Long id) {
        msg = mediaMapper.getLikeItNumber(id);
        clearMap();
        map.put("number", msg);
        return map;
    }

    @Override
    public Object updateAddLikeIt(Long id) {
        Long likeItNumber = (Long) mediaMapper.getLikeItNumber(id);
        likeItNumber += 1;
        mediaMapper.updateAddLikeIt(likeItNumber, id);
        msg = mediaMapper.getLikeItNumber(id);
        clearMap();
        map.put("number", msg);
        return map;
    }

    @Override
    public Object updateSubLikeIt(Long id) {
        Long likeItNumber = (Long) mediaMapper.getLikeItNumber(id);
        likeItNumber -= 1;
        mediaMapper.updateSubLikeIt(likeItNumber, id);
        msg = mediaMapper.getLikeItNumber(id);
        clearMap();
        map.put("number", msg);
        return map;
    }

    @Override
    public Object deleteMediaById(Long id) {
        foreignDelete.setMediaUserIdPersonalColumnIdByMediaId(id);
        mediaMapper.deleteById(id);
        clearMap();
        map.put("id", id);
        return map;
    }

    @Override
    public Object newMediaSave(Media media) {
        msg = mediaMapper.save(media);
        return msg;
    }

    @Override
    public Object updateMedia(Media media) {
        msg = mediaMapper.save(media);
        return msg;
    }
}
