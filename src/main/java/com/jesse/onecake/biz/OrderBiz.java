package com.jesse.onecake.biz;

import com.jesse.onecake.biz.base.BaseBiz;
import com.jesse.onecake.entity.Order;
import com.jesse.onecake.mapper.OrderMapper;
import org.springframework.stereotype.Service;


@Service
public class OrderBiz extends BaseBiz<OrderMapper,Order> {
}
