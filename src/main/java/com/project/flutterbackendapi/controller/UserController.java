package com.project.flutterbackendapi.controller;

import com.project.flutterbackendapi.common.config.ApiResponse;
import com.project.flutterbackendapi.model.request.UserRequestDTO;
import com.project.flutterbackendapi.model.response.UserResponseDTO;
import com.project.flutterbackendapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    @PostMapping("/register")
    public ApiResponse<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO response = userService.registerUser(userRequestDTO);
        return ApiResponse.createSuccess(response);
    }


}
