package com.example.ourdevboard.domain.post;

import com.example.ourdevboard.domain.Timestamped;
import com.example.ourdevboard.domain.dto.ReplyRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reply extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private Long postId;

    public Reply(ReplyRequestDto requestDto){
        this.content = requestDto.getContent();
        this.writer = requestDto.getWriter();
        this.postId = requestDto.getPostId();
    }

    public void update(ReplyRequestDto requestDto){
        this.content = requestDto.getContent();
    }
}
