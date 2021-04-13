package com.example.vote.service;

import com.example.vote.config.CustomerUserDetails;
import com.example.vote.entity.user;
import com.example.vote.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    protected userMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        user user = userMapper.getUserByName(userName);
        String authority = new String();
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println(user);
        if(user.username.equals("admin"))
            authority="admin";
        else
            authority="user";
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
        CustomerUserDetails customerUserDetails = new CustomerUserDetails(user);
        customerUserDetails.setAuthorities(authorities);
        return customerUserDetails;
    }
}
