package com.seaweed.hm.infrastucture.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SimpleMessageEventListener {

    @EventListener
    @Async
    public void notify(SimpleMessageEvent event){
        log.info(event.getMessage());
    }
}
