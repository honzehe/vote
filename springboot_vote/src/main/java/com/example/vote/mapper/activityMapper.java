package com.example.vote.mapper;

import com.example.vote.entity.activity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface activityMapper {

    @Select("select * from activity order by deadline desc")
    public List<activity> getAllActivities();

    @Select("select * from activity where Id = #{Id}")
    public activity getActivityDetail(Integer id);

    @Insert("insert into activity(title, publictime, image, introduction, deadLine) values(#{title}, #{publictime}, #{image}, #{introduction},#{deadLine}) ")
    public int AddActivity(activity activity);

    @Select("select id from activity order by id desc limit 1")
    public int getActivityId();

    @Insert("insert into voted_activity(uId, aId) values(#{uId}, #{aId})" )
    public int voteActivity(Integer uId, Integer aId) throws Exception;

    @Select("select aId from voted_activity where uId = #{uId}")
    public List<Integer> votedActivity(Integer uId);

    @Select("select * from activity where Id in (select aId from voted_activity where uId = #{id}) order by publictime")
    public List<activity> getVotedActivities(Integer id);
}
