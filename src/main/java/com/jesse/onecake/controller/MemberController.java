package com.jesse.onecake.controller;

import com.jesse.onecake.biz.UserBiz;
import com.jesse.onecake.controller.base.BaseController;
import com.jesse.onecake.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MemberController extends BaseController<UserBiz, User> {

    @RequestMapping("/")
    public String member() {
        return "member/member";
    }

    @RequestMapping("/order")
    public String order() {
        return "member/order";
    }
}
