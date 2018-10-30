package com.jesse.onecake.controller;

import com.jesse.onecake.biz.UserBiz;
import com.jesse.onecake.controller.base.BaseController;
import com.jesse.onecake.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "manage")
public class ManageController extends BaseController<UserBiz,User> {

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
}