package com.jesse.onecake.biz;

import com.jesse.onecake.biz.base.BaseBiz;
import com.jesse.onecake.entity.Cake;
import com.jesse.onecake.enums.CakeEnum;
import com.jesse.onecake.mapper.CakeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CakeBiz extends BaseBiz<CakeMapper,Cake> {

    @Autowired private ManageBiz manageBiz;

    public String disableOrNormalizeProduct(String cakeId, Model model) {
        Cake cake = this.selectById(cakeId);
        if ("normal".equals(cake.getStatus())) {
            cake.setStatus(CakeEnum.DISABLED.getValue());
        } else {
            cake.setStatus(CakeEnum.NORMAL.getValue());
        }
        this.updateById(cake);
        return this.manageBiz.productManage(model);
    }
}
