package com.jesse.onecake.mapper;

import com.jesse.onecake.entity.CartDetail;
import com.jesse.onecake.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartDetailMapper extends BaseMapper<CartDetail> {

    List<CartDetail> findCartDetailByUserId(@Param("userId") String userId);

}