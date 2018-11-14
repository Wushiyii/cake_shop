package com.jesse.onecake.controller;

import com.jesse.onecake.biz.OrderBiz;
import com.jesse.onecake.controller.base.BaseController;
import com.jesse.onecake.entity.CakeOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;
import java.util.Date;

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

    @RequestMapping(value = "/cancelOrder/{orderId}")
    public String cancelOrder (@PathVariable("orderId")String orderId,Model model) {
        return this.biz.cancelOrder(orderId,model);
    }

    @RequestMapping(value = "/payOrder/{orderId}")
    public String payOrder (@PathVariable("orderId")String orderId,Model model) {
        return this.biz.payOrder(orderId,model);
    }
    @RequestMapping(value = "/deliveryOrder/{orderId}")
    public String deliveryOrder (@PathVariable("orderId")String orderId,Model model) {
        return this.biz.deliveryOrder(orderId,model);
    }
}
