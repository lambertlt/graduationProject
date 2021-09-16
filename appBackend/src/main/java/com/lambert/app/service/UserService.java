package com.lambert.app.service;

import com.lambert.app.mapper.UserMapper;
import com.lambert.app.model.Role;
import com.lambert.app.model.User;
import com.lambert.app.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

    public Optional<User> loadUserByUserId(Long id) {
        Optional<User> user = userMapper.findById(1L);
        if (user == null) {
            System.out.println("用户不存在");
        }
        return user;
    }

    public HashMap getUserDetail(String username, Long id) {
        UserDetails userDetails = null;
        Optional<User> user = null; // Optional 主要用于防止返回空指针
        if (username != null) {
            userDetails = loadUserByUsername(username);
            Message message = new Message(200, "1", userDetails.toString());
            return message.returnMsg();
        } else if (id != null) {
            user = loadUserByUserId(id);
            Message message = new Message(200, "1", user.toString());
            return message.returnMsg();
        }
        Message message = new Message(400, "请求参数错误", user.toString());
        return message.returnMsg();
    }

    public HashMap createUser(User user) {
        if (user.getUsername() != null && user.getPassword() != null && user.getPower() != null) {
//            User user = new User();
//            user.setUsername("小黄鸭");
//            user.setPassword("yayaya");
            user.setId(-1L);
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);
//            user.setPower(1);
            List<Role> rs1 = new ArrayList<>();
            Role r1 = new Role();
            r1.setId(1L);
            if (user.getPower() == 1) {
                r1.setId(1L);
                r1.setName("ROLE_admin");
                r1.setNameZh("管理员");
            } else if (user.getPower() == 2) {
                r1.setId(2L);
                r1.setName("ROLE_user");
                r1.setNameZh("普通用户");
            }
            rs1.add(r1);
            user.setRoles(rs1);
            User user1 = userMapper.save(user);
            Message message = new Message(200, "", user1.toString());
            return message.returnMsg();
        }

        Message message = new Message(200, "", "".toString());
        return message.returnMsg();
    }

    public User save(User user) {
        return userMapper.save(user);
    }


}
