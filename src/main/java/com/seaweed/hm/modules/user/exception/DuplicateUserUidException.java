package com.seaweed.hm.modules.user.exception;

public class DuplicateUserUidException extends Exception{
    public DuplicateUserUidException(String message){
        super(message);
    }
}
