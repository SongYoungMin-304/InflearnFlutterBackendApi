package com.project.flutterbackendapi.service;


import com.project.flutterbackendapi.common.exception.NotFoundException;
import com.project.flutterbackendapi.entity.User;
import com.project.flutterbackendapi.model.CustomUserDetail;
import com.project.flutterbackendapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserAccount(username).orElseThrow(() -> new NotFoundException("User not found with username: " + username));

        return new CustomUserDetail(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 가장 많이 쓰이는 방식
    }
}
