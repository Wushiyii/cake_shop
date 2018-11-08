package com.jesse.onecake.biz;

import com.jesse.onecake.biz.base.BaseBiz;
import com.jesse.onecake.common.config.security.UserUtils;
import com.jesse.onecake.entity.CakeOrder;
import com.jesse.onecake.entity.OrderDetail;
import com.jesse.onecake.entity.User;
import com.jesse.onecake.mapper.CakeOrderMapper;
import com.jesse.onecake.mapper.OrderDetailMapper;
import com.jesse.onecake.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MemberBiz extends BaseBiz<UserMapper,User> {

    public String info(Model model) {
        User user = this.mapper.findByName(UserUtils.getUserName());
        model.addAttribute("currentUser",user);
        return "/member/info";

    }

    public String changeInfo(User user, Model model) {
        User currentUser = this.mapper.findByName(UserUtils.getUserName());
        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setPhone(user.getPhone());
        currentUser.setGender(user.getGender());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());
        this.updateSelectiveById(currentUser);
        return info(model);
    }
}
