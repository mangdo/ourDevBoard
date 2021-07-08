package com.example.ourdevboard.controller;

import com.example.ourdevboard.domain.dto.*;
import com.example.ourdevboard.security.UserDetailsImpl;
import com.example.ourdevboard.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/reply")
@RestController
public class ReplyRestController {
    private final ReplyService replyService;

    // 댓글 목록 조회
    @GetMapping("/post/{postId}")
    public List<ReplyResponseDto> getPostsList(@PathVariable Long postId){
        return replyService.findReplyList(postId);
    }

    // 댓글 등록
    @PostMapping("")
    public Long registerPost(@RequestBody ReplyRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        requestDto.setWriter(userDetails.getUsername());
        return replyService.save(requestDto);
    }

    // 특정 댓글 조회
    @GetMapping("/{id}")
    public ReplyResponseDto getPost(@PathVariable Long id){
        return replyService.findById(id);
    }

    // 특정 댓글 삭제
    @DeleteMapping("/{id}")
    public Long deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        replyService.delete(id, userDetails.getUsername());
        return id;
    }

    // 특정 댓글 수정
    @PutMapping("/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody ReplyRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return replyService.update(id, requestDto, userDetails.getUsername());
    }

}
