package com.jesse.onecake.mapper;

import com.jesse.onecake.entity.OrderDetail;
import com.jesse.onecake.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    List<OrderDetail> getOrderDetail(@Param("userId") String UserId);

    List<OrderDetail> getOrderDetailToBePaid(@Param("userId") String UserId);
}