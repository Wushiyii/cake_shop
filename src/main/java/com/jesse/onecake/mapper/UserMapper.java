package com.jesse.onecake.mapper;

import com.jesse.onecake.entity.User;
import com.jesse.onecake.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from User where name = #{name}")
    User findByName(@Param("name") String name);
}
