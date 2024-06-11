package com.seaweed.hm.application.user.exception;

public class DuplicateUserUidException extends Exception{
    public DuplicateUserUidException(String message){
        super(message);
    }
}
