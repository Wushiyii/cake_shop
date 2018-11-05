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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return this.biz.getCartDetail(model);
    }

    @RequestMapping(value = "/changeCartQuantity",method = RequestMethod.POST)
    public String changeCartQuantity(String id,String operation,Model model) {
        System.out.println("asdasd");
        return null;
    }

}
