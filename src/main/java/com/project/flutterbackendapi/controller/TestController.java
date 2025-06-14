package com.project.flutterbackendapi.controller;

import com.project.flutterbackendapi.config.ApiResponse;
import com.project.flutterbackendapi.model.response.TestResponseDTO;
import com.project.flutterbackendapi.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;


    @GetMapping("/test")
    public ApiResponse<TestResponseDTO> test() {

        TestResponseDTO response
                = TestResponseDTO.builder()
                .id(1L)
                .name("Test Name")
                .build();

        return ApiResponse.createSuccess(response);
    }

    @GetMapping("/test2/{id}")
    public ApiResponse<TestResponseDTO> test2(@PathVariable Long id) {
        return ApiResponse.createSuccess(testService.findById(id).toResponseDTO());
    }

    @GetMapping("/test3/{id}")
    public ApiResponse<TestResponseDTO> test3(@PathVariable Long id) {
        return ApiResponse.createSuccess(testService.findV2ById(id).toResponseDTO());
    }

}
