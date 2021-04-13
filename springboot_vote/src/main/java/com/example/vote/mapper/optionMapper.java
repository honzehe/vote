package com.example.vote.mapper;

import com.example.vote.entity.option;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface optionMapper {

    @Insert("insert into `option`(description,poll,aId) values(#{description}, #{poll}, #{aId})")
    public int AddOption(option option);

    @Select("select * from `option` where aId = #{id}")
    public List<option>  getOptions(Integer id);

    @Update("update `option` set poll = poll+1 where id = #{id}")
    public int updatePoll(Integer id);

    @Select("select poll from `option` where aId = #{id}")
    public List<Integer> getPolls(Integer id);

    @Delete("delete from `option` where id = #{id}")
    public int deleteOption(Integer id);
}
