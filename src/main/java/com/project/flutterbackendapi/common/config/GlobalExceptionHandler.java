package com.project.flutterbackendapi.common.config;

import com.project.flutterbackendapi.common.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = {"com.project.flutterbackendapi"})
public class GlobalExceptionHandler {
    @ExceptionHandler({
            NotFoundException.class,
    })
    public ApiResponse<?> NotFoundException(RuntimeException exception){
        log.error("NotFoundException Check" + exception.getMessage());
        return ApiResponse.createError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

}
