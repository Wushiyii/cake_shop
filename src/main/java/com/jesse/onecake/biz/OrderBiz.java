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
import tk.mybatis.mapper.entity.Example;
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
        List<Cart> carts = this.cartMapper.selectAll();
        if (carts.size() == 0) {
            model.addAttribute("cartList",carts);
           return "checkout";
        }
        User user = userMapper.findByName(UserUtils.getUserName());
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
        List<CakeOrder> cakeOrders = this.selectAll();
        model.addAttribute("orderList", cakeOrders);
        return "/member/order";
    }

    @Transactional
    public String cancelOrder(String orderId, Model model) {
        CakeOrder cakeOrder = this.selectById(orderId);
        User user = userMapper.findByName(UserUtils.getUserName());
//        Example example = new Example(OrderDetail.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("orderId",orderId);
//        List<OrderDetail> orderDetails = this.orderDetailMapper.selectByExample(example);
//        if (orderDetails.size() != 0) {
//           orderDetails.forEach(orderDetail -> {
//               this.orderDetailMapper.deleteByPrimaryKey(orderDetail);
//           });
//        }
//        this.delete(cakeOrder);
        cakeOrder.setStatus(OrderStatusEnum.CANCELED.getValue());
        cakeOrder.setUpdateUser(user.getUsername());
        cakeOrder.setUpdateTime(new Date());
        this.updateById(cakeOrder);
        return getOrder(model);
    }

    public String payOrder(String orderId, Model model) {
        CakeOrder cakeOrder = this.selectById(orderId);
        User user = userMapper.findByName(UserUtils.getUserName());
        cakeOrder.setStatus(OrderStatusEnum.PAID.getValue());
        cakeOrder.setReceiveStatus(OrderStatusEnum.NOT_RECEIVED.getValue());
        cakeOrder.setUpdateUser(user.getUsername());
        cakeOrder.setUpdateTime(new Date());
        this.updateById(cakeOrder);
        List<CakeOrder> cakeOrders = this.selectAll();
        model.addAttribute("orderList", cakeOrders);
        return "/member/member";
    }

    public String getOrderInfos(Model model) {
        Example example = new Example(CakeOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status","TO_BE_PAID");//查询还未支付的订单
        List<CakeOrder> cakeOrders = this.selectByExample(example);
        example.clear();
        criteria.andEqualTo("status","PAID");//查询已经支付的订单
        List<CakeOrder> receiveOrderList  = this.selectByExample(example);
        model.addAttribute("orderList",cakeOrders);
        model.addAttribute("receiveOrderList",receiveOrderList);
        return "member/member";
    }
}
