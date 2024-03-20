package com.seaweed.hm.common.controller;

import com.seaweed.hm.modules.auth.service.AuthService;
import com.seaweed.hm.modules.user.model.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;

public class DefaultController {
    public UserDTO getLoginUser(){
        ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
        UserDTO loginUser = (UserDTO) httpSession.getAttribute(AuthService.SESSION_LOGIN_NAME);

        return loginUser;
    }

    public final int DEFAULT_FAIL_STATUS_CODE = 0;
    public final int DEFAULT_SUCCESS_STATUS_CODE = 1;

    public ResponseEntity buildResponse() {
        return buildResponse(DEFAULT_SUCCESS_STATUS_CODE);
    }

    public ResponseEntity buildResponse(String message) {
        return buildResponse(DEFAULT_SUCCESS_STATUS_CODE,message);
    }

    public ResponseEntity buildResponse(int statusCode){
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",statusCode);
        ResponseEntity response = new ResponseEntity(responseBody,HttpStatus.OK);
        return response;
    }

    public ResponseEntity buildResponse(int statusCode,String message){
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",statusCode);
        responseBody.put("message",message);
        ResponseEntity response = new ResponseEntity(responseBody,HttpStatus.OK);
        return response;
    }

    public ResponseEntity buildResponse(Object any) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",DEFAULT_SUCCESS_STATUS_CODE);
        responseBody.put("data",any);
        ResponseEntity response = new ResponseEntity(responseBody,HttpStatus.OK);
        return response;
    }
    public ResponseEntity buildResponse(int statusCode, Object any) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",statusCode);
        responseBody.put("data",any);
        ResponseEntity response = new ResponseEntity(responseBody,HttpStatus.OK);
        return response;
    }

    public ResponseEntity buildResponse(int statusCode,String message,Object any){
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",statusCode);
        responseBody.put("message",message);
        responseBody.put("data",any);
        ResponseEntity response = new ResponseEntity(responseBody,HttpStatus.OK);
        return response;
    }

    public ResponseEntity buildFailResponse(){
        return buildResponse(DEFAULT_FAIL_STATUS_CODE);
    }

    public ResponseEntity buildFailResponse(String message){
        return buildResponse(DEFAULT_FAIL_STATUS_CODE,message);
    }


    public ResponseEntity buildResponseBadError() {
        ResponseEntity response = new ResponseEntity(HttpStatus.BAD_REQUEST);
        return response;
    }
    public ResponseEntity buildResponseInternalError() {
        ResponseEntity response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
