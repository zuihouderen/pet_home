package com.example.webpet.service;

import com.example.webpet.entity.User;
import com.example.webpet.entity.UserExample;
import com.example.webpet.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public List<User> getUsers(UserExample userExample){
        return userMapper.selectByExample(userExample);
    }

    public long countUsers(UserExample userExample) {
        return userMapper.countByExample(userExample);
    }

    public int newUser(User user) {
        return userMapper.insertSelective(user);
    }

    public int deleteUser(UserExample userExample) {
        return userMapper.deleteByExample(userExample);
    }

    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
