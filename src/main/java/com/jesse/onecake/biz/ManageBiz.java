package com.jesse.onecake.biz;

import com.jesse.onecake.biz.base.BaseBiz;
import com.jesse.onecake.entity.CakeOrder;
import com.jesse.onecake.entity.User;
import com.jesse.onecake.mapper.CakeOrderMapper;
import com.jesse.onecake.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ManageBiz extends BaseBiz<UserMapper,User> {

    @Autowired
    private CakeOrderMapper cakeOrderMapper;

    public String orderManage(Model model) {
        List<CakeOrder> cakeOrders = cakeOrderMapper.selectAll();
        model.addAttribute("orderList",cakeOrders);
        return "manage/order-manage";
    }
}
