package com.example.ourdevboard.util.exception;

public class SignupRequestException extends IllegalArgumentException{
    public SignupRequestException(String message){
        super(message);
    }
}
