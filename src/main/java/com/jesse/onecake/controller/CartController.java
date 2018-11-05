package com.jesse.onecake.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jesse.onecake.biz.CartBiz;
import com.jesse.onecake.controller.base.BaseController;
import com.jesse.onecake.entity.Cart;
import com.jesse.onecake.mapper.CartDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "cart")
public class CartController extends BaseController<CartBiz,Cart> {

    @Autowired private CartDetailMapper cartDetailMapper;

    @RequestMapping(value = "/addCart",method = RequestMethod.POST)
    public @ResponseBody
    Object addCart (String cakeId, String quantity) {
        return this.biz.addCart(cakeId,Integer.valueOf(quantity));
    }
}
