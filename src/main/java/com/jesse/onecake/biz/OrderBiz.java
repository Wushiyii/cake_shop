package com.jesse.onecake.biz;

import com.jesse.onecake.OrderStatusEnum;
import com.jesse.onecake.biz.base.BaseBiz;
import com.jesse.onecake.common.config.security.UserUtils;
import com.jesse.onecake.dto.OrderDTO;
import com.jesse.onecake.entity.*;
import com.jesse.onecake.mapper.*;
import com.jesse.onecake.service.generator.id.provider.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderBiz extends BaseBiz<CakeOrderMapper, CakeOrder> {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CartDetailMapper cartDetailMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private IdService idService;
    @Autowired
    private CakeMapper cakeMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Transactional
    public String purchaseAll(Model model) {
        User user = userMapper.findByName(UserUtils.getUserName());
        CakeOrder order = new CakeOrder();
        order.setUserId(user.getId().toString());
        order.setStatus(OrderStatusEnum.TO_BE_CHECK.getValue());
        order.setCreateTime(new Date());
        order.setCreateUser(user.getUsername());
        order.setId(idService.genId());
        List<CartDetail> cartDetails = this.cartDetailMapper.selectCartDetailByUserName(UserUtils.getUserName());
        cartDetails.forEach(cartDetail -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order.getId().toString());
            orderDetail.setCakeId(cartDetail.getCakeId());
            orderDetail.setQuantity(cartDetail.getQuantity());
            Cake cake = cakeMapper.selectByPrimaryKey(cartDetail.getCakeId());
            orderDetail.setPrice(cake.getPrice());
            orderDetail.setAddress("to be done");
            orderDetail.setId(idService.genId());
            try {
                this.orderDetailMapper.insertSelective(orderDetail);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            this.insertSelective(order);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        //删除购物车数据
        Cart removeCart = cartMapper.findCartByUserId(user.getId().toString());
        try {
            cartMapper.deleteByPrimaryKey(removeCart);
            cartDetails.forEach(cartDetail -> {
                cartDetailMapper.deleteByPrimaryKey(cartDetail);
            });
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return getOrder(model);
    }

    public String getOrderDetail(Model model) {
        User user = userMapper.findByName(UserUtils.getUserName());
        List<OrderDetail> orderDetail = this.orderDetailMapper.getOrderDetail(user.getId().toString());
        model.addAttribute("orderList", orderDetail);
        return "/member/order";
    }

    public String getOrder(Model model) {
        User user = userMapper.findByName(UserUtils.getUserName());
        List<CakeOrder> cakeOrders = this.selectAll();
        List<OrderDTO> orderList = new ArrayList<>();
        cakeOrders.forEach(cakeOrder -> {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(cakeOrder.getId().toString());
            orderDTO.setCreateTime(cakeOrder.getCreateTime());
            switch (cakeOrder.getStatus()) {
                case "0":
                    orderDTO.setStatus("待支付");
                    orderDTO.setOperation("取消订单");
                    break;
                case "1":
                    orderDTO.setStatus("已支付");
                    orderDTO.setOperation("取消订单");
                    break;
                case "2":
                    orderDTO.setStatus("已取消");
                    break;
            }
            orderList.add(orderDTO);
        });
        model.addAttribute("orderList", orderList);
        return "/member/order";
    }
}
