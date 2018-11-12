package com.jesse.onecake.controller;

import com.jesse.onecake.biz.ManageBiz;
import com.jesse.onecake.biz.UserBiz;
import com.jesse.onecake.controller.base.BaseController;
import com.jesse.onecake.entity.User;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "manage")
public class ManageController extends BaseController<ManageBiz,User> {

    @RequestMapping("/")
    public String manage() {

        return "manage/index";
    }
    @RequestMapping("/form")
    public String form() {

        return "manage/form";
    }
    @RequestMapping("/chart")
    public String chart() {

        return "manage/chart";
    }
    @RequestMapping("/table-list")
    public String tableList() {
        return "manage/table-list";
    }
    @RequestMapping("/tables")
    public String tables() {
        return "manage/tables";
    }
    @RequestMapping("/error")
    public String error() {
        return "manage/404";
    }

    @RequestMapping("/order-manage")
    public String orderManage(Model model) {
        return this.biz.orderManage(model);
    }

    @RequestMapping("/user-manage")
    public String userManage(Model model) {
        return this.biz.userManage(model);
    }
    /**
     * 打开新增/修改用户详情页面
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/createOrUpdateUser/{userId}")
    public String createOrUpdateUser(@PathVariable("userId") String userId, Model model) {
        if ("1".equals(userId)) {
            return "/manage/user-detail";
        } else {
            return this.biz.createOrUpdateUser(userId, model);
        }
    }
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public String updateUser(User user,String userGroup, Model model) {
        return this.biz.updateUser(user,userGroup,model);
    }

}
