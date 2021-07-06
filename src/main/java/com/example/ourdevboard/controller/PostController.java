package com.example.ourdevboard.controller;

import com.example.ourdevboard.domain.dto.PostDetailResponseDto;
import com.example.ourdevboard.domain.dto.PostRequestDto;
import com.example.ourdevboard.domain.dto.PostSimpleResponseDto;
import com.example.ourdevboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class PostController {
    private final PostService postService;

    // 게시글 목록 조회
    @GetMapping("/api/posts")
    public List<PostSimpleResponseDto> getPostsList(){
        return postService.findAll();
    }

    // 게시글 등록
    @PostMapping("/api/posts")
    public Long registerPost(@RequestBody PostRequestDto requestDto){
        return postService.save(requestDto);
    }

    // 특정 게시글 조회
    @GetMapping("/api/posts/{id}")
    public PostDetailResponseDto getPost(@PathVariable Long id){
        return postService.findById(id);
    }

    // 특정 게시글 삭제
    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id){
        postService.delete(id);
        return id;
    }

    // 특정 게시글 수정
    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

}
