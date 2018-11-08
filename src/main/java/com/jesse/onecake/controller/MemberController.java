package com.jesse.onecake.controller;

import com.jesse.onecake.biz.MemberBiz;
import com.jesse.onecake.biz.OrderBiz;
import com.jesse.onecake.biz.UserBiz;
import com.jesse.onecake.controller.base.BaseController;
import com.jesse.onecake.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MemberController extends BaseController<MemberBiz, User> {

    @Autowired private OrderBiz orderBiz;

    @RequestMapping("/")
    public String member(Model model) {
        return this.orderBiz.getOrderInfos(model);
    }

    @RequestMapping("/order")
    public String order(Model model) {
        return this.orderBiz.getOrder(model);
    }
    @RequestMapping("/info")
    public String info(Model model) {
        return this.biz.info(model);
    }
    @RequestMapping("/changeInfo")
    public String changeInfo(User user,Model model) {
        return this.biz.changeInfo(user,model);
    }

    @RequestMapping("/changePass")
    public String changePass(Model model) {
        return "/member/changePass";
    }
}
