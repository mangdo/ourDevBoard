package com.example.ourdevboard.controller;

import com.example.ourdevboard.domain.dto.SignupRequestDto;
import com.example.ourdevboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/user/*")
@Controller
public class UserController {

    private final UserService userService;

    // 회원 가입 페이지
    @GetMapping("/signup")
    public void signup() {
    }

    // 회원 가입 요청 처리
    @PostMapping("/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/";
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public void login() {

    }

}
