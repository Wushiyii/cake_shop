package com.jesse.onecake.biz;

import com.jesse.onecake.biz.base.BaseBiz;
import com.jesse.onecake.common.config.security.UserUtils;
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
import org.springframework.ui.Model;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Slf4j
public class UserBiz extends BaseBiz<UserMapper,User> {

    @Autowired private CakeMapper cakeMapper;

    @Autowired private IdService idService;


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
        user.setId(idService.genId());
        user.setUserGroup(0);//普通用户
        try {
            this.insertSelective(user);
        }catch (Exception e){
            log.error(e.getMessage());
            return "error";
        }
        return "login";
    }

    public String info(Model model) {
        User user = this.mapper.findByName(UserUtils.getUserName());
        model.addAttribute("currentUser",user);
        return "/user/info";

    }

    public String changeInfo(User user, Model model) {
        User currentUser = this.mapper.findByName(UserUtils.getUserName());
        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setPhone(user.getPhone());
        currentUser.setGender(user.getGender());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());
        this.updateSelectiveById(currentUser);
        return info(model);
    }

    public String changePassword(String oldPass, String newPass) {
        User user = this.mapper.findByName(UserUtils.getUserName());
        BCryptPasswordEncoder bcr = new BCryptPasswordEncoder();
        if (oldPass != null && bcr.matches(oldPass,user.getPassword())) {
            user.setPassword(bcr.encode(newPass));
            this.updateSelectiveById(user);
        }
        return "/user/changePass";
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bcrp = new BCryptPasswordEncoder();
        System.out.println(bcrp.encode("123"));
        System.out.println(bcrp.encode("123"));

    }
}
