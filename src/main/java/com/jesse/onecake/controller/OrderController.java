package com.jesse.onecake.controller;

import com.jesse.onecake.biz.OrderBiz;
import com.jesse.onecake.controller.base.BaseController;
import com.jesse.onecake.entity.CakeOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "order")
public class OrderController extends BaseController<OrderBiz,CakeOrder> {

    @RequestMapping(value = "/purchaseAll",method = RequestMethod.GET)
    public String purchaseAll (Model model) {
        return this.biz.purchaseAll(model);
    }

    @RequestMapping(value = "/OrderDetail")
    public String getOrderDetail (Model model) {
        return this.biz.getOrderDetail(model);
    }
}
