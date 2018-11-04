package com.jesse.onecake.controller;

import com.jesse.onecake.biz.OrderBiz;
import com.jesse.onecake.controller.base.BaseController;
import com.jesse.onecake.entity.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "order")
public class OrderController extends BaseController<OrderBiz,Order> {

    @RequestMapping(value = "/purchase",method = RequestMethod.POST)
    public String purchase (String id,String quantity) {
        System.out.println(id+","+quantity);
        return null;
    }
}
