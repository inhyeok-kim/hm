package com.seaweed.hm.comm.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
public class APIResponse {
    private int code = 0;
    private String message = "";
    private Object data = "";

    public static class Code {
        public static final int UN_AUTHENTICATED = -100;

    }

}
    
    
