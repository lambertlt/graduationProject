package com.lambert.fun.new_app.service.serviceImpl;

import com.lambert.fun.new_app.dao.ForeignDelete;
import com.lambert.fun.new_app.dao.MediaMapper;
import com.lambert.fun.new_app.entity.Media;
import com.lambert.fun.new_app.service.MediaService;
import com.lambert.fun.new_app.util.FileUtil;
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

    public Media fullObject(Media media) {
        Media media1 = new Media();
        if (media.getId() != null && !media.getId().toString().trim().equals("")) {
            media1 = mediaMapper.getById(media.getId());
            media1.setTitle(media.getTitle());
            media1.setImg(media.getImg());
        }
        return media1;
    }

    @Override
    public Object getMediaById(Long id) {
        msg = mediaMapper.getById(id);
        return msg;
    }

    @Override
    public Object getMediaUserId(Long id) {
        msg = mediaMapper.getMediaUserId(id);
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
        clearMap();
        foreignDelete.setMediaUserIdPersonalColumnIdByMediaId(id);
        Media media = mediaMapper.getById(id);
        msg = FileUtil.deleteFile(media.getPath()); // 用于删除实体存在的文件
        System.out.println("deleteFile error: " + msg);
        mediaMapper.deleteById(id);
        map.put("id", id);
        return map;
    }

    @Override
    public Object newMediaSave(Media media) {
        msg = mediaMapper.save(media);
        return msg;
    }

    @Override
    // 只修改标题和宣传图
    public Object updateMedia(Media media) {
        Media newMedia = fullObject(media);
        msg = mediaMapper.save(newMedia);
        return msg;
    }
}
