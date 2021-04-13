package com.example.vote.service;

import com.example.vote.entity.activity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ActivityService {
    public PageInfo<activity> getAllActivities(Integer page, Integer size);

    public activity getActivityDetail(Integer id);

    public int AddActivity(activity activity);

    public int getActivityId();

    public int voteActivity(Integer uId, Integer aId);

    public PageInfo<activity> getVotedActivities(Integer id, Integer page, Integer size);
}
