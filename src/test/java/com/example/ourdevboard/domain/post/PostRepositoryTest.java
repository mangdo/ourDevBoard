package com.example.ourdevboard.domain.post;

import com.example.ourdevboard.domain.dto.PostRequestDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    // save() 와 findAll() 테스트
    @Test
    @DisplayName("게시물 저장하고 읽기")
    public void saveAndRead(){
        // given
        String title = "테스트 게시글1";
        String content = "테스트 본문1";
        String writer = "작성자1";
        PostRequestDto requestDto = new PostRequestDto(title, content, writer);
        Post post = new Post(requestDto);
        postRepository.save(post);

        // when
        List<Post> postsList = postRepository.findAll();

        // then
        Post posts = postsList.get(0);
        System.out.println(posts.getTitle());
        System.out.println(posts.getContent());
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

}
