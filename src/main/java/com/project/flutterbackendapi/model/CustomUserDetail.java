package com.project.flutterbackendapi.model;

import com.project.flutterbackendapi.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetail implements UserDetails {
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // UserType을 Spring Security 권한 객체로 변환
        return Collections.singletonList(
                new SimpleGrantedAuthority(user.getAccountType().name())
        );
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }
}
