package com.example.ourdevboard.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private String writer;
}
