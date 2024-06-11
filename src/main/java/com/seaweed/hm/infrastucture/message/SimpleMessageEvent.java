package com.seaweed.hm.infrastucture.message;

import lombok.Getter;

public class SimpleMessageEvent {
    @Getter
    private String message;
    public SimpleMessageEvent(String message){
        this.message = message;
    }
}
