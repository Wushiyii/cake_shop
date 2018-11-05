package com.jesse.onecake.mapper;

import com.jesse.onecake.entity.Cart;
import com.jesse.onecake.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {

    Cart findCartByUserId(@Param("userId") String userId);

}