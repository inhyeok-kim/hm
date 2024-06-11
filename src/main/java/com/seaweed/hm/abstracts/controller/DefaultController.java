package com.seaweed.hm.abstracts.controller;


import com.seaweed.hm.comm.http.response.APIResponse;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class DefaultController {

    @ExceptionHandler(NotFoundException.class)
    public APIResponse handleNotFoundedException(NotFoundException e){
        return APIResponse.builder()
                .message(e.getMessage())
                .code(-1)
                .build();
    }
}
