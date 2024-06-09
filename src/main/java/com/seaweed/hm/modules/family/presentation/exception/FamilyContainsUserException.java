package com.seaweed.hm.modules.family.presentation.exception;

import lombok.Getter;

@Getter
public class FamilyContainsUserException extends Exception{
    public FamilyContainsUserException(String message){
        super(message);
    }

}
