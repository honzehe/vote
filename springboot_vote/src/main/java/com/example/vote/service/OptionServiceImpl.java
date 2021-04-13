package com.example.vote.service;

import com.example.vote.entity.option;
import com.example.vote.mapper.optionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class OptionServiceImpl implements OptionService{

    @Autowired
    public optionMapper optionMapper;

    @Resource
    private RedisTemplate<Integer, List<option>> redisTemplate;

    @Override
    public int AddOption(option option) {
        System.out.println(option);
        redisTemplate.delete(option.getAId());
        return optionMapper.AddOption(option);
    }

    @Override
    public List<option> getOptions(Integer id) {
        List<option> options = new ArrayList<>();
        options = redisTemplate.opsForValue().get(id);
        if(options==null){
            options = optionMapper.getOptions(id);
            redisTemplate.opsForValue().set(id,options);
        }else{
            System.out.println("成功从缓存获取");
        }
     //   System.out.println(id);
   //     System.out.println(options);
        return options;
    }

    @Override
    public int updatePoll(Integer aId, Integer id) {

        System.out.println(id);
        redisTemplate.delete(aId);
        return optionMapper.updatePoll(id);
    }

    @Deprecated
    @Override
    public List<Integer> getPolls(Integer id) {
//        List<Integer> polls = new ArrayList<>();
//
//        polls = redisTemplate.opsForValue().get(id);
//        if(polls==null){
//            polls=optionMapper.getPolls(id);
//            redisTemplate.opsForValue().set(id,polls);
//        }else{
//            System.out.println("成功从缓存获得");
//        }
//        System.out.println(id);
        return null;
    }

    @Override
    public int deleteOption(Integer aId, Integer id) {
        redisTemplate.delete(aId);
        return optionMapper.deleteOption(id);
    }
}
