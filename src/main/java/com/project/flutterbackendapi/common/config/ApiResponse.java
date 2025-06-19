package com.project.flutterbackendapi.common.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

    private static final String SUCCESS_STATUS = "success";
    private static final String CREATE_STATUS ="create_create";
    private static final String MODIFY_STATUS ="success_modify";
    private static final String DELETE_STATUS ="success_delete";

    private static final String FAIL_STATUS = "fail";
    private static final String ERROR_STATUS = "error";

    private String status;
    private int statusCode;

    private T data;
    private String message;

    public static <T> ApiResponse<T> createSuccess(T data) {
        return new ApiResponse<>(HttpStatus.OK.value(), SUCCESS_STATUS, data, "");
    }

    public static <T> ApiResponse<T> createSuccess(T data, String message) {
        return new ApiResponse<>(HttpStatus.OK.value(), SUCCESS_STATUS, data, message);
    }

    public static ApiResponse<?> createError(int statusCode, String message) {
        return new ApiResponse<>(statusCode, FAIL_STATUS,  message);
    }

    public ApiResponse(int statusCode, String status, T data) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = "";
        // 앱에서 쓰는 라이브러리 요구 조건으로 data 값 없어도 "" 처리
        if(data==null){
            this.data = (T)"";
        }else{
            this.data = data;
        }
    }

    public ApiResponse(int statusCode, String status, T data, String message) {
        this.statusCode = statusCode;
        this.status = status;

        // 앱에서 쓰는 라이브러리 요구 조건으로 data 값 없어도 "" 처리
        if(data==null){
            this.data = (T)"";
        }else{
            this.data = data;
        }

        // 앱에서 쓰는 라이브러리 요구 조건으로 message 값 없어도 "" 처리
        if(message==null){
            this.message = "";
        }else{
            this.message = message;
        }
        this.message = message;
    }


}
