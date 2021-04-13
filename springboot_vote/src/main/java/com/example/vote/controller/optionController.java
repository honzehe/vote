package com.example.vote.controller;

import com.example.vote.entity.option;
import com.example.vote.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class optionController {

    @Autowired
    OptionService optionService;

    @PostMapping("/admin/AddOption")
    public int AddOption(@RequestBody option option){
        return optionService.AddOption(option);
    }

    @GetMapping("/getOptions/{id}")
    public List<option> getOptions(@PathVariable("id") Integer id){
        return optionService.getOptions(id);
    }

    @PutMapping("/updatePoll")
    public int updatePoll(Integer aId, Integer id){
        return optionService.updatePoll(aId,id);
    }

    @GetMapping("/getPolls/{id}")
    public List<Integer> getPolls(@PathVariable("id") Integer id){
        return optionService.getPolls(id);
    }

    @PostMapping("/admin/deleteOption/{aId}/{id}")
    public int deleteOption(@PathVariable("aId") Integer aId, @PathVariable("id") Integer id){
        return optionService.deleteOption(aId,id);
    }
}
