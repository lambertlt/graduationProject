package com.lambert.fun.new_app.service.serviceImpl;

import com.lambert.fun.new_app.dao.UserMapper;
import com.lambert.fun.new_app.entity.Role;
import com.lambert.fun.new_app.entity.User;
import com.lambert.fun.new_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    UserMapper userMapper;

    private Object msg;

    @Override
    // 查找用户 name
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

    @Override
    // 查找用户 id
    public User loadUserByUserid(Long id) {
        User user = userMapper.getById(id);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

    @Override
    // 创建用户
    public Object newUserSave(User user) {
        if (userMapper.findUserByUsername(user.getUsername()) != null) {
            // 不等于空表示用户已存在
            return "用户已存在";
        }
        String username = user.getUsername();
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        Long power = 3L;
        List<Role> roles = new ArrayList<>();
        roles.add(Role.getUser());
        User user1 = User.getUser(username, password, power, roles);
        User newUser = userMapper.save(user1);
        return newUser;
    }

    @Override
    // 删除用户
    public Object deleteUser(Long id) {
        userMapper.deleteById(id);
        Map map = new HashMap();
        map.put("id", id);
        return map;
    }

    @Override
    // 修改密码
    public Object updateUserPassword(User user) {
        // 修改密码
        User oldUser = loadUserByUserid(user.getId()); // 要修改用户的原信息
        oldUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        msg = userMapper.save(oldUser);
        return msg;
    }

    @Override
    // 修改权限
    public Object updateUserPower(User user) {
        List<Role> roles = new ArrayList<>();
        Long power = user.getPower();
        User nowUser = userMapper.getById(user.getId());
        if (power == 1L) {
            roles.add(Role.getAdmin());
        } else if (power == 2L) {
            roles.add(Role.getMedia());
        } else if (power == 3L) {
            roles.add(Role.getUser());
        }
        nowUser.setPower(user.getPower());
        nowUser.setRoles(roles);
        User newUser = userMapper.save(nowUser);
        return newUser;
    }
}
