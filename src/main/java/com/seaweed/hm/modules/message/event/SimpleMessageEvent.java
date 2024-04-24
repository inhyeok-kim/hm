package com.seaweed.hm.modules.message.event;

import lombok.Getter;

public class SimpleMessageEvent {
    @Getter
    private String message;
    public SimpleMessageEvent(String message){
        this.message = message;
    }
}
