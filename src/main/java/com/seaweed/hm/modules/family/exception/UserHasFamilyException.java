package com.seaweed.hm.modules.family.exception;

import lombok.Getter;

@Getter
public class UserHasFamilyException extends Exception{
    public UserHasFamilyException(String message){
        super(message);
    }

}
