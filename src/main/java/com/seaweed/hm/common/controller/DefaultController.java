package com.seaweed.hm.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DefaultController {
    public ResponseEntity responseOk() {
        ResponseEntity response = new ResponseEntity(HttpStatus.OK);
        return response;
    }

    public ResponseEntity responseOk(Object any) {
        ResponseEntity response = new ResponseEntity(any,HttpStatus.OK);
        return response;
    }
    public ResponseEntity responseBad() {
        ResponseEntity response = new ResponseEntity(HttpStatus.BAD_REQUEST);
        return response;
    }
    public ResponseEntity responseError() {
        ResponseEntity response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
