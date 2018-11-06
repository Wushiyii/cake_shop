package com.jesse.onecake.mapper;

import com.jesse.onecake.entity.CartDetail;
import com.jesse.onecake.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartDetailMapper extends BaseMapper<CartDetail> {

    List<CartDetail> selectCartDetailByUserId(@Param("userId") String userId);

    Integer selectCountCartDetailByUserId(@Param("userId") String userId);

    List<CartDetail> selectCartDetailByUserName(@Param("username") String username);

}