package com.jesse.onecake.controller;

import com.jesse.onecake.biz.UserBiz;
import com.jesse.onecake.controller.base.BaseController;
import com.jesse.onecake.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController<UserBiz, User> {


//    @RequestMapping("/login")
//    public String login(String username,String password){
//        return this.biz.login(username,password);
//    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(String username,String phone,String pass){
        return this.biz.register(username,phone,pass);
    }

}
