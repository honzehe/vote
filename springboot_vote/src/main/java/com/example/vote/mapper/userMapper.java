package com.example.vote.mapper;

import com.example.vote.entity.user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface userMapper {

    @Select("select * from user where userName=#{username}")
    public user getUserByName(String username);

    @Insert("Insert into user(username, password, mail, gender, image, phoneNumber, authority) values(#{username}, #{password}," +
            "#{mail}, #{gender}, #{image}, #{phoneNumber}, #{authority})")
    public int regist(user user);

    @Select("select * from user")
    public List<user> getUsers();

    @Select("select * from user where Id = #{id}")
    public user getUserById(Integer id);

    @Update("update user set authority = #{authority} where id = #{id}")
    public int changeAuthority(Integer id, Integer authority);
}
