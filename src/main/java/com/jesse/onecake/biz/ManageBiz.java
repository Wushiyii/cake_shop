package com.jesse.onecake.biz;

import com.jesse.onecake.biz.base.BaseBiz;
import com.jesse.onecake.entity.Cake;
import com.jesse.onecake.entity.CakeOrder;
import com.jesse.onecake.entity.User;
import com.jesse.onecake.mapper.CakeMapper;
import com.jesse.onecake.mapper.CakeOrderMapper;
import com.jesse.onecake.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ManageBiz extends BaseBiz<UserMapper,User> {

    @Autowired private CakeOrderMapper cakeOrderMapper;
    @Autowired private UserMapper userMapper;
    @Autowired private CakeMapper cakeMapper;

    public String orderManage(Model model) {
        List<CakeOrder> cakeOrders = cakeOrderMapper.selectAll();
        model.addAttribute("orderList",cakeOrders);
        return "manage/order-manage";
    }

    public String userManage(Model model) {
        List<User> users = userMapper.selectAll();
        model.addAttribute("userList",users);
        return "/manage/user-manage";
    }

    public String createOrUpdateUser(String userId, Model model) {
        User user = this.userMapper.selectByPrimaryKey(userId);
        model.addAttribute("user",user);
        return "/manage/user-detail";
    }

    public String updateUser(User user,String userGroup, Model model) {
            User updateUser = this.selectById(user.getId());
            updateUser.setName(user.getName());
            updateUser.setAge(user.getAge());
            updateUser.setPhone(user.getPhone());
            updateUser.setGender(user.getGender());
            updateUser.setAddress(user.getAddress());
            updateUser.setEmail(user.getEmail());
            if ("on".equals(userGroup)) {
                updateUser.setUserGroup("ROLE_ADMIN");
            } else {
                updateUser.setUserGroup("ROLE_USER");
            }

            this.updateSelectiveById(updateUser);
            return "redirect:/manage/user-manage";
    }

    public String productManage(Model model) {
        List<Cake> cakes = this.cakeMapper.selectAll();
        model.addAttribute("productList",cakes);
        return "/manage/product-manage";
    }
}
