package com.example.vote.service;

import com.example.vote.entity.user;
import com.example.vote.mapper.userMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    userMapper userMapper;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public user getUserByName(String username) {

        return userMapper.getUserByName(username);
    }

    @Override
    public int regist(user user) {
        user.password = passwordEncoder.encode(user.password);
        System.out.println(user);
        System.out.println("注册成功");
        return userMapper.regist(user);
    }

    @Override
    public PageInfo<user> getUsers(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<user> list = userMapper.getUsers();
        PageInfo<user> users = new PageInfo<>(list);
        return users;
    }

    @Override
    public user getUserById(Integer id){
        System.out.println(id);
        return userMapper.getUserById(id);
    }

    @Override
    public int changeAuthority(Integer id, Integer authority) {
        System.out.println(id);
        System.out.println(authority);
        return userMapper.changeAuthority(id,authority);
    }
}
