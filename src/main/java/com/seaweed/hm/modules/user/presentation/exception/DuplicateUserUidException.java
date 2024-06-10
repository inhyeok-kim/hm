package com.seaweed.hm.modules.user.presentation.exception;

public class DuplicateUserUidException extends Exception{
    public DuplicateUserUidException(String message){
        super(message);
    }
}
