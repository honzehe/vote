package com.example.vote.service;

import com.example.vote.entity.activity;
import com.example.vote.mapper.activityMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    activityMapper activityMapper;

    @Override
    public PageInfo<activity> getAllActivities(Integer page, Integer size) {
        System.out.println("获取所有活动信息");
        PageHelper.startPage(page,size);
        List<activity> list = activityMapper.getAllActivities();
        PageInfo<activity> activities = new PageInfo<>(list);
        return activities;
    }

    @Override
    public activity getActivityDetail(Integer id) {
        System.out.println("查看详情页");
        return activityMapper.getActivityDetail(id);
    }

    @Override
    public int getActivityId() {
        return activityMapper.getActivityId();
    }

    @Override
    public int AddActivity(activity activity) {
        System.out.println("添加了活动");
        System.out.println(activity);
        return activityMapper.AddActivity(activity);
    }

    @Override
    public int voteActivity(Integer uId, Integer aId) {
        System.out.println(uId);
        System.out.println(aId);
        int res;
        try{
            res =  activityMapper.voteActivity(uId,aId);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
        return res;
    }

    @Override
    public PageInfo<activity> getVotedActivities(Integer id, Integer page, Integer size) {

        PageHelper.startPage(page,size);

        List<activity> list = activityMapper.getVotedActivities(id);
        System.out.println(id);
        PageInfo<activity> activities = new PageInfo<>(list);
        return activities;
    }
}
