package com.seaweed.hm.comm.exception.advice;

import com.seaweed.hm.comm.component.http.response.APIResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler
    public APIResponse hadleException(HttpMessageNotReadableException e){
        return APIResponse.builder().code(-400).message("잘못된 요청입니다.").build();
    }

    @ExceptionHandler
    public APIResponse hadleException(BadRequestException e){
        return APIResponse.builder().code(-400).message("잘못된 요청입니다.").build();
    }
}
