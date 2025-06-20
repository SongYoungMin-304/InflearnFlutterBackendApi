package com.project.flutterbackendapi.controller;

import com.project.flutterbackendapi.common.config.ApiResponse;
import com.project.flutterbackendapi.common.util.JwtTokenProvider;
import com.project.flutterbackendapi.entity.User;
import com.project.flutterbackendapi.model.request.UserLoginRequestDTO;
import com.project.flutterbackendapi.model.request.UserRequestDTO;
import com.project.flutterbackendapi.model.response.UserResponseDTO;
import com.project.flutterbackendapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ApiResponse<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO response = userService.registerUser(userRequestDTO);
        return ApiResponse.createSuccess(response);
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {

        User user = userService.getUserByUserAccount(userLoginRequestDTO.getUserAccount());

        if (!passwordEncoder.matches(userLoginRequestDTO.getUserPassword(),user.getUserPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return ApiResponse.createSuccess(jwtTokenProvider.createToken(user.getUserAccount(), Collections.singleton(
                new SimpleGrantedAuthority(user.getUserType().name()))));
    }
}
