package com.jesse.onecake.controller;

import com.jesse.onecake.biz.CakeBiz;
import com.jesse.onecake.common.response.TableResultResponse;
import com.jesse.onecake.controller.base.BaseController;
import com.jesse.onecake.entity.Cake;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "cake")
public class CakeController extends BaseController<CakeBiz,Cake> {

    @RequestMapping("/getBannerList")
    public @ResponseBody
    TableResultResponse<Cake> getBannerList(){
//        Page<Object> result = PageHelper.startPage(query.getPageNumber(), query.getPageSize());
        Example example = new Example(Cake.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("banner","1");
        List<Cake> cakes = this.biz.selectByExample(example);
        return new TableResultResponse<>(cakes.size(), cakes);
    }

    @RequestMapping("/home")
    public String home(){
        return "index";
    }
    @RequestMapping("/checkout")
    public String checkout(){
        return "checkout";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/allCake")
    public String allCake(Model model) {
//        Cake cake = new Cake();
//        cake.setName("sad");
//        cake.setCategory("14");
//        cake.setBanner(4);
//        cake.setPrice(4.33);
//        model.addAttribute("cakeList",cake);
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
        mav.addObject("model",cake);
        mav.setViewName("single");
        return mav;
    }

    @RequestMapping("/")
    public String loadBanner(Model model) {
//        Page<Object> result = PageHelper.startPage(query.getPageNumber(), query.getPageSize());
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



    @RequestMapping(value = "/searchByCategory",method = RequestMethod.GET)
    public @ResponseBody TableResultResponse<Cake> searchByCategory(String batchCategory) {
        List<Cake> cakes = null;
        String[] category = batchCategory.split(",");
        List<String> list = Arrays.asList(category);
        Example example = new Example(Cake.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("category",list);
        cakes = this.biz.selectByExample(example);
        return new TableResultResponse<>(cakes.size(), cakes);
    }

    @RequestMapping(value = "/searchProduct",method = RequestMethod.POST)
    public String searchProduct(String name,Model model){
//        Example example = new Example(Cake.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andLike("name",name);
//        List<Cake> cakes = this.biz.selectByExample(example);
        System.out.println("ajaxTest");
        Cake cake1 = new Cake();
        cake1.setName(name);
        cake1.setCategory("12");
        cake1.setBanner(4);
        cake1.setPrice(4.33);
        Cake cake2 = new Cake();
        cake2.setName("ewq");
        cake2.setCategory("3");
        cake2.setBanner(5);
        cake2.setPrice(4.34);
        List<Cake> list = new ArrayList<>();
        list.add(cake1);
        list.add(cake2);
        model.addAttribute("aa",list);
        return "shop::div1";
    }


}
