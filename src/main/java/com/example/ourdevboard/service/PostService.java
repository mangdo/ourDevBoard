package com.example.ourdevboard.service;

import com.example.ourdevboard.domain.dto.PostDetailResponseDto;
import com.example.ourdevboard.domain.dto.PostRequestDto;
import com.example.ourdevboard.domain.dto.PostSimpleResponseDto;
import com.example.ourdevboard.domain.post.Post;
import com.example.ourdevboard.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    // Create Post
    @Transactional
    public Long save(PostRequestDto requestDto){
        Post post = new Post(requestDto);
        return postRepository.save(post).getId();
    }

    // Delete Post
    @Transactional
    public void delete(Long id){
        postRepository.deleteById(id);
    }

    // Read Post List
    @Transactional(readOnly=true) // 트랜잭션 범위는 유지하되 조회기능만 남겨서 조회속도가 개선
    public List<PostSimpleResponseDto> findAll(){
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(post -> new PostSimpleResponseDto(post))
                .collect(Collectors.toList());
        // postsRepository.findAll()는 List<Post>를 반환하는데
        // 이를 List<PostSimpleResponseDto>로 바꿔서 반환
    }

    // Read One Post By Id
    @Transactional(readOnly=true) // 트랜잭션 범위는 유지하되 조회기능만 남겨서 조회속도가 개선
    public PostDetailResponseDto findById(Long id){
        Post entity = postRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 게시물이 없습니다. id = "+id));
        return new PostDetailResponseDto(entity);
    }

    // Update Post By Id
    @Transactional
    public Long update(Long id, PostRequestDto requestDto){
        Post product = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        product.update(requestDto);
        return id;
    }

}
