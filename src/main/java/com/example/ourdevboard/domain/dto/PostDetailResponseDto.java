package com.example.ourdevboard.domain.dto;

import com.example.ourdevboard.domain.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDetailResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String writer;

    public PostDetailResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.writer = post.getWriter();
    }
}
