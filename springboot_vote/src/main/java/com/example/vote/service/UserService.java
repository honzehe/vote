package com.example.vote.service;

import com.example.vote.entity.user;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    public user getUserByName(String username);
    public int regist(user user);
    public PageInfo<user> getUsers(Integer page, Integer size);
    public user getUserById(Integer id);
    public int changeAuthority(Integer id, Integer authority);
}
