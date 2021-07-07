package com.example.ourdevboard.controller;

import com.example.ourdevboard.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/posts/*")
@Controller
public class PostController {
    @GetMapping("/register")
    public void register() {
    }

    @GetMapping("/detail")
    public void showDetail() {
    }

    @GetMapping("/update")
    public void home() {

    }

}
