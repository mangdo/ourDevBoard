package com.example.ourdevboard.util.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 부가처리인 예외처리를 분리시켰다!
@ControllerAdvice
public class SignupExceptionHandler {

    @ExceptionHandler(value = { SignupRequestException.class })
    public String handleApiRequestException(SignupRequestException ex, Model model) {

        model.addAttribute("signupFail", ex.getMessage());

        return "user/signup";
    }
}
