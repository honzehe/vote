package com.example.vote.service;

import com.example.vote.entity.option;

import java.util.List;

public interface OptionService {
    public int AddOption(option option);

    public List<option> getOptions(Integer id);

    public int updatePoll(Integer aId, Integer id);

    @Deprecated
    public List<Integer> getPolls(Integer id);

    public int deleteOption(Integer aId, Integer id);
}
