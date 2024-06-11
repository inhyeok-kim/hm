package com.seaweed.hm.application.family.exception;

import lombok.Getter;

@Getter
public class FamilyContainsUserException extends Exception{
    public FamilyContainsUserException(String message){
        super(message);
    }

}
