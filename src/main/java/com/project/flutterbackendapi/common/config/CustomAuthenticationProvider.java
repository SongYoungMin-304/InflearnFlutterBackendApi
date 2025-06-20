package com.project.flutterbackendapi.common.config;

import com.project.flutterbackendapi.entity.User;
import com.project.flutterbackendapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@RequiredArgsConstructor
@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        log.info("CustomAuthenticationProvider.authenticate() called");

        String username = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();

        User user = userService.getUserByUserAccount(authentication.getName());

        if (!passwordEncoder.matches(rawPassword, user.getUserPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return new UsernamePasswordAuthenticationToken(
                username,
                rawPassword,
                Collections.singletonList(
                        new SimpleGrantedAuthority(user.getUserType().name())
                )
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
