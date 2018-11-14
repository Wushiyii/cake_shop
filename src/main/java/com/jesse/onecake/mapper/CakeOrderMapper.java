package com.jesse.onecake.mapper;

import com.jesse.onecake.entity.CakeOrder;
import com.jesse.onecake.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CakeOrderMapper extends BaseMapper<CakeOrder> {

    List<CakeOrder> selectCakeOrderByUserIdAndStatus(@Param("userId")String userId, @Param("status")String status);

    List<CakeOrder> selectLastWeek();
}