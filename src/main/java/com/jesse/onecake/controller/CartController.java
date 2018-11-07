package com.jesse.onecake.controller;

import com.jesse.onecake.biz.CartBiz;
import com.jesse.onecake.common.config.security.UserUtils;
import com.jesse.onecake.controller.base.BaseController;
import com.jesse.onecake.entity.Cart;
import com.jesse.onecake.entity.User;
import com.jesse.onecake.mapper.CartDetailMapper;
import com.jesse.onecake.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.*;
import java.util.*;

@Controller
@RequestMapping(value = "cart")
public class CartController extends BaseController<CartBiz,Cart> {

    @Autowired private CartDetailMapper cartDetailMapper;
    @Autowired private UserMapper userMapper;

    @RequestMapping(value = "/addCart",method = RequestMethod.POST)
    public @ResponseBody
    Object addCart (String cakeId, String quantity) {
        return this.biz.addCart(cakeId,Integer.valueOf(quantity));
    }

    @RequestMapping(value = "/cartCount",method = RequestMethod.POST)
    public @ResponseBody
    Object cartCount () {
        Map<String,Integer> map = new HashMap<>();
        User user = userMapper.findByName(UserUtils.getUserName());
        Integer count = cartDetailMapper.selectCountCartDetailByUserId(user.getId().toString());
        map.put("count",count);
        return map;
    }

    @RequestMapping(value = "/getCartDetail",method = RequestMethod.GET)
    public String getCartDetail(Model model) {
        return this.biz.getCartDetail(model,true);
    }

    @RequestMapping(value = "/changeCartQuantity",method = RequestMethod.POST)
    public String changeCartQuantity(String cakeId,String operation,Model model) {
        return this.biz.changeCartQuantity(cakeId,operation,model);
    }

    @RequestMapping(value = "/removeOne/{cakeId}",method = RequestMethod.GET)
    public String single(@PathVariable("cakeId") String cakeId,Model model) {
        if (cakeId == null) {
            throw new RuntimeException("删除购物车商品ID为空,请确认");
        }
        return this.biz.removeOne(cakeId,model);
    }
}
