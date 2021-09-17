package com.lambert.jpa.service;

import com.lambert.jpa.mapper.RoleMapper;
import com.lambert.jpa.mapper.UserMapper;
import com.lambert.jpa.model.Role;
import com.lambert.jpa.model.User;
import com.lambert.jpa.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名查找用户信息
        User user = userMapper.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

    public Message getUserDetail(String name, Long id) {
        // 查找用户信息
        User user = null;
        Message message = null;
        if (id != null) {
            try {
                user = userMapper.getById(id);
                user.setPassword("********");
                message = new Message(200, "1", user.toString());
            } catch (Exception e) {
                System.out.println(e);
                message = new Message(400, "请求参数不存在", "");
                return message;
            }
        } else if (name != null) {
            user = (User) loadUserByUsername(name);
            user.setPassword("********");
            message = new Message(200, "1", user.toString());
        }
        return message;
    }

    public Message save(User user) {
        // 创建用户、更新用户
        User user1 = null;
        Role role = null;
        Message message = null;
        List<Role> rs1 = new ArrayList<>();
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        if (user.getId() != null) {
            // 当id不为空，就是修改用户，可以修改用户密码、权限
            User user2 = userMapper.getById(user.getId());
            if (user.getPower() == 1) {
                role = roleMapper.getById(1L);
            } else if (user.getPower() == 2) {
                role = roleMapper.getById(2L);
            }
            if (user.getPassword() == null) {
                // 密码为空就是修改了用户权限
                String password = user2.getPassword();
                user.setPassword(password);
//                user.setPassword(new BCryptPasswordEncoder().encode(password));
            } else {
                String password = user.getPassword();
                user.setPassword(new BCryptPasswordEncoder().encode(password));
            }
            if (user.getUsername() == null) {
                String username = user2.getUsername();
                user.setUsername(username);
            }
        } else {
            // 当id为空，就是创建用户,默认为用户权限
            role = roleMapper.getById(2L);
            user.setId(-1L);
//            new BCryptPasswordEncoder(10);
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        rs1.add(role);
        user.setRoles(rs1);
        try {
            user1 = userMapper.save(user);
        } catch (Exception e) {
            message = new Message(400, "参数错误", "");
            return message;
        }
        user1.setPassword("********");
        message = new Message(200, "1", user1.toString());
        return message;
    }

    public Message delete(Long id) {
        // 删除用户
        User user = null;
        Message message = null;
        try {
            userMapper.deleteById(id);
        } catch (Exception e) {
            message = new Message(400, "请求参数不存在", "");
            return message;
        }
        message = new Message(200, "1", "".toString());
        return message;
    }
}
