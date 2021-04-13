package com.example.vote.service;

import com.example.vote.entity.message;
import com.example.vote.mapper.messageMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class messageServiceImpl implements messageService{

    @Autowired
    protected messageMapper messageMapper;

    @Resource
    protected RedisTemplate<String, List<message>> redisTemplate;

    @Override
    public List<message> getMessage(String username) {
        List<message> messages = redisTemplate.opsForValue().get(username);
        if(messages == null){
            messages = messageMapper.getMessages(username);
            redisTemplate.opsForValue().set(username,messages);
        }
//        PageHelper.startPage(page,size);
//        PageInfo<message> messages = new PageInfo<>(list);
        return messages;
    }

    @Override
    public int senMessage(message message, Integer aId) {
        List<String> users = messageMapper.getUsername(aId);
        for(String username : users){
            redisTemplate.delete(username);
            message.setUsername(username);
            messageMapper.sendMessage(message);
        }
        return 0;
    }
}
