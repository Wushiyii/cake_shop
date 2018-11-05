package com.jesse.onecake.biz;

import com.jesse.onecake.biz.base.BaseBiz;
import com.jesse.onecake.common.utils.SHA256Encoder;
import com.jesse.onecake.entity.Cake;
import com.jesse.onecake.entity.User;
import com.jesse.onecake.mapper.CakeMapper;
import com.jesse.onecake.mapper.UserMapper;
import com.jesse.onecake.service.generator.id.provider.IdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Slf4j
public class UserBiz extends BaseBiz<UserMapper,User> {

    @Autowired private CakeMapper cakeMapper;

    @Autowired private IdService idService;

    public String test() {
        User user1 = this.selectById(1);
        long l = idService.genId();
        System.out.println(l);
        Cake muse = cakeMapper.findByName("muse");
        System.out.println(muse);
        
        return "checkout";
    }

    public String register(String username, String phone, String pass) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        List<User> users = this.selectByExample(example);
        if (users.size() != 0) {
           return "error";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(pass));
//        user.setGroup("user"); //user用户组
        user.setId(idService.genId());
        user.setUserGroup(0);
        try {
            this.insertSelective(user);
        }catch (Exception e){
            log.error(e.getMessage());
            return "error";
        }
        return "login";
    }

//    public String login(String username, String password) {
//        Example example = new Example(User.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("username",username);
//        List<User> users = this.selectByExample(example);
//        if(users.size() != 0){
//            User user = users.get(0);
//            if (user.getPassword()!=null && user.getPassword().equals(SHA256Encoder.encode(password))){
//                return "index";
//            }
//        }
//        return "login";
//    }
}
