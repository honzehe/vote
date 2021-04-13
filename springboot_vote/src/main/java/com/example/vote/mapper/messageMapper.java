package com.example.vote.mapper;

import com.example.vote.entity.message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface messageMapper {

    @Select("select * from message where username = #{username} order by sendtime")
    public List<message> getMessages(String username);

    @Select("select username from user where Id in (select uId from voted_activity where aId = #{aId})")
    public List<String> getUsername(Integer aId);

    @Insert("insert into message(message,sendtime,username) values(#{message}, #{sendtime}, #{username})")
    public int sendMessage(message message);
}
