package com.jesse.onecake.mapper;

import com.jesse.onecake.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from User where name = #{name}")
    User findByName(@Param("name") String name);
}
