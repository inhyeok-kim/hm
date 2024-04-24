package com.seaweed.hm.comm.abstracts.controller;

import com.seaweed.hm.comm.component.http.response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class OpenAPIController {

    @Autowired
    public ResponseBuilder responseBuilder;

}
