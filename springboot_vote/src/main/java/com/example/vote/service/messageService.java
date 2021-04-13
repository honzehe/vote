package com.example.vote.service;

import com.example.vote.entity.message;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface messageService {

    public List<message> getMessage(String username);

    public int senMessage(message message, Integer aId);
}
