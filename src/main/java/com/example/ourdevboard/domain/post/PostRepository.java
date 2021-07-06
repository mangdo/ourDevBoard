package com.example.ourdevboard.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 생성일자 기준 내림차순 정렬
    List<Post> findAllByOrderByCreatedAtDesc();
}
