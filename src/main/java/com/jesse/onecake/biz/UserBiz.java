package com.jesse.onecake.biz;

import com.jesse.onecake.entity.User;
import com.jesse.onecake.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lxm.idgenerator.service.intf.IdService;

@Service
public class UserBiz extends BaseBiz<UserMapper,User> {

//    @Autowired
//    private IdService idService;

    public String test() {
        User user1 = this.selectById(1);
//        long l = idService.genId();
//        System.out.println(l);
        return "checkout";
    }
}
