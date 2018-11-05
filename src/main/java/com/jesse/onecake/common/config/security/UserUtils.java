package com.jesse.onecake.common.config.security;

import com.jesse.onecake.entity.User;
import com.jesse.onecake.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtils {


    public static UserDetails getUserDetails(){
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    public static String getUserName(){
        return getUserDetails().getUsername();
    }

}