package com.seaweed.hm.application.family.exception;

import lombok.Getter;

@Getter
public class UserHasFamilyException extends Exception{
    public UserHasFamilyException(String message){
        super(message);
    }

}
