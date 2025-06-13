package com.project.flutterbackendapi.controller;

import com.project.flutterbackendapi.config.ApiResponse;
import com.project.flutterbackendapi.model.response.TestResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public ApiResponse<TestResponseDTO> test() {

        TestResponseDTO response
                = TestResponseDTO.builder()
                .id(1L)
                .name("Test Name")
                .build();

        return ApiResponse.createSuccess(response);
    }

}
