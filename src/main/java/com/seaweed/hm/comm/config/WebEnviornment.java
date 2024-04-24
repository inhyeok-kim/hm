package com.seaweed.hm.comm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebEnviornment {
    @Value("${spring.profiles.active[0]}")
    private String activeProfile;

    public boolean isTest(){
        return activeProfile.equals("test");
    }

    public boolean isLocal(){
        return activeProfile.equals("local");
    }
    public boolean isProd(){
        return activeProfile.equals("prod");
    }

}
