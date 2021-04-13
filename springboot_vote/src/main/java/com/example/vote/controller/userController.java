package com.example.vote.controller;

import com.example.vote.entity.user;
import com.example.vote.service.MyUserDetailsService;
import com.example.vote.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class userController {

    @Autowired
    UserService userService;

    @Autowired
    MyUserDetailsService userDetailsService;


    @PostMapping("/login")
    public user login(@RequestBody user user){
        return (com.example.vote.entity.user) userDetailsService.loadUserByUsername(user.username);
//        return null;
    }

    @GetMapping("/getUserByName/{username}")
    public user getUserByName(@PathVariable("username") String username){
        return userService.getUserByName(username);
    }

    @PostMapping("/regist")
    public int regist(@RequestBody user user){
        return userService.regist(user);
    }

    @GetMapping("/admin/getUsers/{page}/{size}")
    public PageInfo<user> getUsers(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return userService.getUsers(page,size);
    }

    @GetMapping("/getUserById/{id}")
    public user getUserById(@PathVariable("id") Integer id){
        return userService.getUserById(id);
    }

    @PostMapping("/admin/changeAuthority")
    public int changeAuthority(Integer id, Integer authority){
        return userService.changeAuthority(id,authority);
    }
}
