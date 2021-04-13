package com.example.vote.controller;

import com.example.vote.entity.message;
import com.example.vote.service.messageService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class messageController {

    @Autowired
    messageService messageService;

    @GetMapping("/getMessage/{username}")
    public List<message> getMessage(@PathVariable("username") String username){
        return messageService.getMessage(username);
    }

    @PostMapping("/admin/sendMessage/{aId}")
    public int sendMessage(@RequestBody message message, @PathVariable("aId") Integer aId){
        return messageService.senMessage(message,aId);
    }
}
