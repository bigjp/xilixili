package com.xilixili.dao;


import com.qf.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    @Select("select * from user where email=#{email}")
    User findUserByUsername(User user);

    @Insert("insert into user (email,password) value (#{email},#{password})")
    int addUser(User user);
}
