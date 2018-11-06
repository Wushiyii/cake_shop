package com.jesse.onecake.biz;

import com.jesse.onecake.biz.base.BaseBiz;
import com.jesse.onecake.common.config.security.UserUtils;
import com.jesse.onecake.dto.CartDTO;
import com.jesse.onecake.entity.Cake;
import com.jesse.onecake.entity.Cart;
import com.jesse.onecake.entity.CartDetail;
import com.jesse.onecake.entity.User;
import com.jesse.onecake.mapper.CakeMapper;
import com.jesse.onecake.mapper.CartDetailMapper;
import com.jesse.onecake.mapper.CartMapper;
import com.jesse.onecake.mapper.UserMapper;
import com.jesse.onecake.service.generator.id.provider.IdService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class CartBiz extends BaseBiz<CartMapper,Cart> {
    @Autowired private UserMapper userMapper;
    @Autowired private CartDetailMapper cartDetailMapper;
    @Autowired private IdService idService;
    @Autowired private CakeMapper cakeMapper;

    @Transactional
    public Object addCart(String cakeId, Integer quantity) {
        String userName = UserUtils.getUserName();
        User user = userMapper.findByName(userName);
        Cart cart = this.mapper.findCartByUserId(user.getId().toString());
        //购物车不为空
        if (cart != null) {
            //查询购物车详情
            List<CartDetail>  cartDetail = cartDetailMapper.selectCartDetailByUserId(user.getId().toString());
            boolean alreadyExist = false;
            //判断是否包含将要添加商品的id
            for (CartDetail detail : cartDetail) {
                //包含
                if (detail.getCakeId() != null && cakeId.equals(detail.getCakeId())) {
                    detail.setQuantity(detail.getQuantity() + quantity);
                    detail.setUpdateDate(new Date());
                    alreadyExist = true;
                    try{
                        cartDetailMapper.updateByPrimaryKeySelective(detail);
                    }catch (RuntimeException e){
                        throw new RuntimeException(e);
                    }
                    break;
                }
            }
            //不包含，新建一条
            if (!alreadyExist) {
                CartDetail extenalDetail = new CartDetail();
                extenalDetail.setCakeId(cakeId);
                extenalDetail.setQuantity(quantity);
                extenalDetail.setCartId(cart.getId().toString());
                extenalDetail.setId(idService.genId());
                extenalDetail.setCreateDate(new Date());
                try{
                    cartDetailMapper.insertSelective(extenalDetail);
                }catch (RuntimeException e){
                    throw new RuntimeException(e);
                }
            }
            //购物车为空
        } else {
            Cart externalCart = new Cart();
            externalCart.setId(idService.genId());
            externalCart.setUserId(user.getId().toString());
            externalCart.setCreateDate(new Date());
            externalCart.setCreateUser(userName);
            CartDetail externalDetail = new CartDetail();
            externalDetail.setId(idService.genId());
            externalDetail.setCartId(externalCart.getId().toString());
            externalDetail.setCakeId(cakeId);
            externalDetail.setQuantity(quantity);
            externalDetail.setCreateDate(new Date());
            try{
                this.mapper.insertSelective(externalCart);
                cartDetailMapper.insertSelective(externalDetail);
            }catch (RuntimeException e){
                throw new RuntimeException(e);
            }
        }
        Integer count = cartDetailMapper.selectCountCartDetailByUserId(user.getId().toString());
        Map<String,Integer> map = new HashMap<>();
        map.put("count",count);
        return map;
    }

    public String getCartDetail(Model model) {
        List<CartDTO> cartDTOList = new ArrayList<>();
        List<CartDetail> cartDetails = this.cartDetailMapper.selectCartDetailByUserName(UserUtils.getUserName());
        Double allPrice = 0.00;
        for (CartDetail detail : cartDetails) {
            Cake cake = this.cakeMapper.selectByPrimaryKey(detail.getCakeId());
            CartDTO dto = new CartDTO();
            BeanUtils.copyProperties(cake,dto);
            dto.setId(Integer.parseInt(cake.getId().toString()));
            dto.setQuantity(detail.getQuantity());
            dto.setTotalPrice(dto.getPrice() * dto.getQuantity());//设置当前一栏商品的总价
            allPrice = dto.getPrice() * dto.getQuantity() + allPrice;
            cartDTOList.add(dto);
        }
        model.addAttribute("cartList",cartDTOList);
        model.addAttribute("allPrice",allPrice);
        return "checkout::cartDetailList";
    }

    public String changeCartQuantity(String cakeId, String operation, Model model) {
        List<CartDetail> cartDetails = this.cartDetailMapper.selectCartDetailByUserName(UserUtils.getUserName());
        for (CartDetail cartDetail : cartDetails) {
            if (cakeId != null && cakeId.equals(cartDetail.getCakeId())) {
                //增加操作
                if("increment".equals(operation)) {
                    cartDetail.setQuantity(cartDetail.getQuantity() + 1);
                //减少操作
                } else {
                    //如果数量只有一个，不执行减少操作
                    if (cartDetail.getQuantity() == 1) {
                        cartDetail.setQuantity(1);
                    } else {
                        cartDetail.setQuantity(cartDetail.getQuantity() - 1);
                    }
                }
                this.cartDetailMapper.updateByPrimaryKey(cartDetail);
            }
        }
        return this.getCartDetail(model);
    }

    public String removeOne(String cakeId,Model model) {
        List<CartDetail> cartDetails = this.cartDetailMapper.selectCartDetailByUserName(UserUtils.getUserName());
        for (CartDetail cartDetail : cartDetails) {
            if(cakeId.equals(cartDetail.getCakeId())) {
                this.cartDetailMapper.deleteByPrimaryKey(cartDetail);
                break;
            }
        }
        return this.getCartDetail(model);
    }
}
