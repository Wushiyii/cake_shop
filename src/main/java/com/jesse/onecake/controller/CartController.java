package com.jesse.onecake.controller;

import com.jesse.onecake.biz.CartBiz;
import com.jesse.onecake.controller.base.BaseController;
import com.jesse.onecake.entity.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "cart")
public class CartController extends BaseController<CartBiz,Cart> {

    @RequestMapping(value = "/addCart",method = RequestMethod.POST)
    public String purchase (String id,String quantity) {
        System.out.println(id+","+quantity);
        return null;
    }
}
