package com.example.vote.config;

import com.example.vote.entity.user;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomerUserDetails extends user implements UserDetails {
    private Collection<? extends GrantedAuthority> authorities;
    public CustomerUserDetails(user user){
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setAuthority(user.getAuthority());
        this.setPassword(user.getPassword());
        this.setMail(user.getMail());
        this.setGender(user.getGender());
        this.setPhoneNumber(user.getPhoneNumber());
        this.setMail(user.getMail());
    }
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
