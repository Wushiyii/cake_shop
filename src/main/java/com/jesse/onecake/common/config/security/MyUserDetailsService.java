package com.jesse.onecake.common.config.security;

import com.jesse.onecake.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

     @Autowired
     private UserMapper userMapper;

    /**
     * 授权的时候是对角色授权，而认证的时候应该基于资源，而不是角色，因为资源是不变的，而用户的角色是会变的
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     com.jesse.onecake.entity.User user = userMapper.findByName(username);
     if (null == user) {
      throw new UsernameNotFoundException(username);
     }
     List<SimpleGrantedAuthority> authorities = new ArrayList<>();
     authorities.add(new SimpleGrantedAuthority(user.getUserGroup()));
     return new User(user.getUsername(), user.getPassword(), authorities);
    }
}