package com.example.ourdevboard.controller;

import org.springframework.stereotype.Controller;
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
