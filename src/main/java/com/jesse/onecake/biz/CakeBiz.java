package com.jesse.onecake.biz;

import com.jesse.onecake.biz.base.BaseBiz;
import com.jesse.onecake.entity.Cake;
import com.jesse.onecake.mapper.CakeMapper;
import org.springframework.stereotype.Service;

@Service
public class CakeBiz extends BaseBiz<CakeMapper,Cake> {

    public void addCart(Integer id) {
        Cake cake = this.selectById(id);
        ///add cart
    }
}
