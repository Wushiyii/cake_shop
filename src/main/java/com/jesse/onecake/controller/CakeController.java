package com.jesse.onecake.controller;

import com.jesse.onecake.biz.CakeBiz;
import com.jesse.onecake.biz.CartBiz;
import com.jesse.onecake.common.response.TableResultResponse;
import com.jesse.onecake.controller.base.BaseController;
import com.jesse.onecake.entity.Cake;
import com.sun.org.apache.bcel.internal.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(value = "cake")
public class CakeController extends BaseController<CakeBiz,Cake> {

    @Autowired private CartBiz cartBiz;

    @RequestMapping("/home")
    public String home(Model model){
        Example example = new Example(Cake.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("banner","1");
        model.addAttribute("bannerList", this.biz.selectByExample(example));
        return "index::productList";
    }
    @RequestMapping("/checkout")
    public String checkout(Model model){
        return cartBiz.getCartDetail(model,false);
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/allCake")
    public String allCake(Model model) {
        return "shop";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/single/{id}")
    public ModelAndView single(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView();
        Cake cake = this.biz.selectById(id);
        mav.addObject("cake",cake);
        mav.setViewName("single");
        return mav;
    }

    @RequestMapping("/")
    public String index(Model model) {
        Example example = new Example(Cake.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("banner","1");
        model.addAttribute("bannerList", this.biz.selectByExample(example));
        return "index";
    }

    @RequestMapping("/addCart")
    public String addCart(Integer id) {
        this.biz.addCart(id);
        return null;
    }


    @PermitAll
    @RequestMapping(value = "/searchByCategory",method = RequestMethod.POST)
    public String searchByCategory(String queryCategory,Model model) {
        if("".equals(queryCategory) || queryCategory == null){
            return "shop::productList";
        }

        String[] category = queryCategory.split(",");
        List<String> list = Arrays.asList(category);
        Example example = new Example(Cake.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("category",list);
        List<Cake> cakes = this.biz.selectByExample(example);
        cakes.sort(Comparator.comparing(Cake::getName));
        model.addAttribute("cakeList",cakes);
        return "shop::productList";
    }

    @RequestMapping(value = "/searchProduct",method = RequestMethod.POST)
    public String searchProduct(String name,Model model){
        Example example = new Example(Cake.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%"+name+"%");
        List<Cake> cakes = this.biz.selectByExample(example);
        model.addAttribute("cakeList",cakes);
        return "shop::productList";
    }
    @RequestMapping("changeView")
    public String changeView() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getAuthorities().toString().equals("[ROLE_ADMIN]")){
            return "/manage/index";//后台登录
        }
        else{
            return "redirect:/cake/";//客户登录
        }
    }

}
