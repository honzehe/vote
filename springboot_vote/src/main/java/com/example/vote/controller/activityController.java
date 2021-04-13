package com.example.vote.controller;

import com.example.vote.entity.activity;
import com.example.vote.service.ActivityService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class activityController {

    @Autowired
    ActivityService activityService;

    @GetMapping("/getAllActivities/{page}/{size}")
    public PageInfo<activity> getAllActivities(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return activityService.getAllActivities(page,size);
    }
    @GetMapping("/getActivityDetail/{id}")
    public activity getActivityDetail(@PathVariable("id") Integer id){
        return activityService.getActivityDetail(id);
    }

    @PostMapping("/admin/AddActivity")
    public int AddActivity(@RequestBody activity activity){
        return activityService.AddActivity(activity);
    }

    @GetMapping("/getActivityId")
    public int getActivityId(){
        return activityService.getActivityId();
    }

    @PutMapping("/voteActivity")
    public int voteActivity(Integer uId, Integer aId){
        return activityService.voteActivity(uId, aId);
    }

    @GetMapping("/getVotedActivities/{id}/{page}/{size}")
    public PageInfo<activity> getVotedActivities(@PathVariable("id") Integer id, @PathVariable("page") Integer page,
    @PathVariable("size") Integer size){
        return activityService.getVotedActivities(id,page,size);
    }
}
