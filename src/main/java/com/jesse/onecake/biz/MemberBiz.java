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

    @Autowired private OrderDetailMapper orderDetailMapper;
    @Autowired private CakeOrderMapper cakeOrderMapper;
    @Autowired private UserMapper userMapper;

    public String getOrderInfos(Model model) {
        User user = userMapper.findByName(UserUtils.getUserName());
//        List<OrderDetail> orderDetails = this.orderDetailMapper.getOrderDetailToBePaid(user.getId().toString());
        Example example = new Example(CakeOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status","TO_BE_PAID");//查询还未支付的订单
        List<CakeOrder> cakeOrders = this.cakeOrderMapper.selectByExample(example);
        model.addAttribute("orderList",cakeOrders);
        return "member/member";
    }
}
