package com.jesse.onecake.biz;

import com.jesse.onecake.enums.OrderStatusEnum;
import com.jesse.onecake.biz.base.BaseBiz;
import com.jesse.onecake.common.config.security.UserUtils;
import com.jesse.onecake.entity.*;
import com.jesse.onecake.mapper.*;
import com.lxm.idgenerator.service.intf.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import tk.mybatis.mapper.entity.Example;

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
        Cart cart = cartMapper.findCartByUserId(user.getId().toString());
        if (cart == null) {
            model.addAttribute("cartList",new ArrayList());
           return "checkout";
        }

        CakeOrder order = new CakeOrder();
        order.setUserId(user.getId().toString());
        order.setStatus(OrderStatusEnum.TO_BE_PAID.getValue());
        order.setReceiveStatus(OrderStatusEnum.NOT_RECEIVED.getValue());
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
            orderDetail.setAddress(user.getAddress());
            orderDetail.setId(idService.genId());
            orderDetail.setCreateDate(new Date());
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
        return "/user/order";
    }

    public String getOrder(Model model) {
        User user = userMapper.findByName(UserUtils.getUserName());
        Example example = new Example(CakeOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",user.getId());
        List<CakeOrder> cakeOrders = this.selectByExample(example);
        model.addAttribute("orderList", cakeOrders);
        return "/user/order";
    }

    @Transactional
    public String cancelOrder(String orderId, Model model) {
        CakeOrder cakeOrder = this.selectById(orderId);
        User user = userMapper.findByName(UserUtils.getUserName());
        if(cakeOrder.getStatus().equals(OrderStatusEnum.PAID.getValue())) {
            cakeOrder.setReceiveStatus(OrderStatusEnum.PAIDCANCELED.getValue());
            cakeOrder.setUpdateUser(user.getUsername());
            cakeOrder.setUpdateTime(new Date());
            this.updateById(cakeOrder);
            return "redirect:/manage/order-manage";//管理订单界面
        } else {
            cakeOrder.setStatus(OrderStatusEnum.CANCELED.getValue());
            cakeOrder.setUpdateUser(user.getUsername());
            cakeOrder.setUpdateTime(new Date());
            this.updateById(cakeOrder);
            return getOrder(model);
        }


    }

    public String payOrder(String orderId, Model model) {
        CakeOrder cakeOrder = this.selectById(orderId);
        User user = userMapper.findByName(UserUtils.getUserName());
        cakeOrder.setStatus(OrderStatusEnum.PAID.getValue());
        cakeOrder.setReceiveStatus(OrderStatusEnum.NOT_RECEIVED.getValue());
        cakeOrder.setUpdateUser(user.getUsername());
        cakeOrder.setUpdateTime(new Date());
        this.updateById(cakeOrder);

        return getOrderInfos(model);
    }

    public String getOrderInfos(Model model) {
        User user = userMapper.findByName(UserUtils.getUserName());
        Example example = new Example(CakeOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status","TO_BE_PAID");//查询还未支付的订单
        criteria.andEqualTo("userId",user.getId());
        List<CakeOrder> cakeOrders = this.selectByExample(example);

        Example example2 = new Example(CakeOrder.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria2.andEqualTo("status","PAID");//查询已经支付的订单
        criteria2.andEqualTo("userId",user.getId());
        List<CakeOrder> receiveOrderList  = this.selectByExample(example2);

        model.addAttribute("orderList",cakeOrders);
        model.addAttribute("receiveOrderList",receiveOrderList);
        return "/user/member";
    }


    public String deliveryOrder(String orderId, Model model) {
        CakeOrder cakeOrder = this.selectById(orderId);
        User user = userMapper.findByName(UserUtils.getUserName());
        cakeOrder.setReceiveStatus(OrderStatusEnum.DELIVERING.getValue());
        cakeOrder.setDeliveryId(user.getId().toString());
        cakeOrder.setDeliveryTime(new Date());
        cakeOrder.setDeliveryUser(user.getUsername());
        this.updateById(cakeOrder);
        List<CakeOrder> cakeOrders = this.selectAll();
        model.addAttribute("orderList",cakeOrders);
        return "manage/order-manage";
    }
}
