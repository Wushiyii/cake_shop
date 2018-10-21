package com.jesse.onecake.controller;

import com.jesse.onecake.biz.UserBiz;
import com.jesse.onecake.controller.base.BaseController;
import com.jesse.onecake.entity.User;
import com.jesse.onecake.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController extends BaseController<UserBiz, User> {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("checkout")
    public String checkout(){
        User jesse = userMapper.findByName("Jesse");
        System.out.println(jesse);
        return "checkout";
    }

    @RequestMapping("test")
    public String test(){
        return this.biz.test();
    }

    @RequestMapping("/")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "Pray");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }
}
