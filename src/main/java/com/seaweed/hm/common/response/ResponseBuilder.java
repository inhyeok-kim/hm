package com.seaweed.hm.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ResponseBuilder {
    public final int DEFAULT_FAIL_STATUS_CODE = -1;
    public final int DEFAULT_SUCCESS_STATUS_CODE = 0;

    public final int DEFAULT_UN_AUTH_CODE = -100;
    
    
    public ResponseEntity responseUnAuthenticated() {
        return responseBadError(DEFAULT_UN_AUTH_CODE,"unAuthenticated");
    }
    public ResponseEntity response() {
        return response(DEFAULT_SUCCESS_STATUS_CODE);
    }

    public ResponseEntity response(String message) {
        return response(DEFAULT_SUCCESS_STATUS_CODE,message);
    }

    public ResponseEntity response(int statusCode){
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",statusCode);
        ResponseEntity response = new ResponseEntity(responseBody, HttpStatus.OK);
        return response;
    }

    public ResponseEntity response(int statusCode,String message){
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",statusCode);
        responseBody.put("message",message);
        ResponseEntity response = new ResponseEntity(responseBody,HttpStatus.OK);
        return response;
    }

    public ResponseEntity response(Object any) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",DEFAULT_SUCCESS_STATUS_CODE);
        responseBody.put("data",any);
        ResponseEntity response = new ResponseEntity(responseBody,HttpStatus.OK);
        return response;
    }
    public ResponseEntity response(int statusCode, Object any) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",statusCode);
        responseBody.put("data",any);
        ResponseEntity response = new ResponseEntity(responseBody,HttpStatus.OK);
        return response;
    }

    public ResponseEntity response(int statusCode,String message,Object any){
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",statusCode);
        responseBody.put("message",message);
        responseBody.put("data",any);
        ResponseEntity response = new ResponseEntity(responseBody,HttpStatus.OK);
        return response;
    }

    public ResponseEntity responseFail(){
        return response(DEFAULT_FAIL_STATUS_CODE);
    }

    public ResponseEntity responseFail(String message){
        return response(DEFAULT_FAIL_STATUS_CODE,message);
    }


    public ResponseEntity responseBadError() {
        ResponseEntity response = new ResponseEntity(HttpStatus.BAD_REQUEST);
        return response;
    }
    public ResponseEntity responseBadError(int statusCode,String message,Object any) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",statusCode);
        responseBody.put("message",message);
        responseBody.put("data",any);
        ResponseEntity response = new ResponseEntity(responseBody,HttpStatus.BAD_REQUEST);
        return response;
    }
    public ResponseEntity responseBadError(int statusCode,String message) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",statusCode);
        responseBody.put("message",message);
        ResponseEntity response = new ResponseEntity(responseBody,HttpStatus.BAD_REQUEST);
        return response;
    }
    public ResponseEntity responseBadError(int statusCode) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",statusCode);
        ResponseEntity response = new ResponseEntity(responseBody,HttpStatus.BAD_REQUEST);
        return response;
    }
    public ResponseEntity responseInternalError() {
        ResponseEntity response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
    public ResponseEntity responseInternalError(int statusCode,String message,Object any) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",statusCode);
        responseBody.put("message",message);
        responseBody.put("data",any);
        ResponseEntity response = new ResponseEntity(responseBody,HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
    public ResponseEntity responseInternalError(int statusCode,String message) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",statusCode);
        responseBody.put("message",message);
        ResponseEntity response = new ResponseEntity(responseBody,HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
    public ResponseEntity responseInternalError(int statusCode) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("code",statusCode);
        ResponseEntity response = new ResponseEntity(responseBody,HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
