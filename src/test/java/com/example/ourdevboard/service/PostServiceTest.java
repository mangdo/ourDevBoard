package com.example.ourdevboard.service;

import com.example.ourdevboard.domain.dto.PostRequestDto;
import com.example.ourdevboard.domain.dto.PostSimpleResponseDto;
import com.example.ourdevboard.domain.post.Post;
import com.example.ourdevboard.domain.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;
    // @Mock이 붙은 목객체를 @InjectMocks이 붙은 객체에 주입시킬 수 있다.


    @Test
    @DisplayName("게시물 저장하고 읽기")
    public void saveAndRead(){
        // given
        PostRequestDto requestDto = new PostRequestDto("테스트게시글1", "테스트본문1","작성자");
        Post post = new Post(requestDto);
        PostRequestDto requestDto2 = new PostRequestDto("테스트게시글2", "테스트본문2", "작성자");
        Post post2 = new Post(requestDto2);

        // when
        when(postRepository.findAllByOrderByCreatedAtDesc()).thenReturn(Stream.of(post, post2).collect(Collectors.toList()));
        List<PostSimpleResponseDto> postsList = postService.findAll();

        // then
        PostSimpleResponseDto posts = postsList.get(0);
        System.out.println(posts.getTitle());
        assertThat(posts.getTitle()).isEqualTo("테스트게시글1");
    }

}
