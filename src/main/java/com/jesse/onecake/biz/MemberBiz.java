package com.jesse.onecake.biz;

import com.jesse.onecake.biz.base.BaseBiz;
import com.jesse.onecake.entity.User;
import com.jesse.onecake.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MemberBiz extends BaseBiz<UserMapper,User> {

    public String getOrderInfos(Model model) {
        model.addAttribute("a","asdasd");
        return "member/member";
    }
}
