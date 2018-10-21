package com.jesse.onecake.mapper;

import com.jesse.onecake.entity.Cake;
import com.jesse.onecake.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CakeMapper extends BaseMapper<Cake> {

    Cake findByName(@Param("name") String name);

}