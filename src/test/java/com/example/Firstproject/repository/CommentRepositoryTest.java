package com.example.Firstproject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;
    @Test
    void findByArticleId() {
        // 1. 예상
        
        // 2. 실제

        // 3. 비교
    }

    @Test
    void findByNickname() {
        // 1. 예상

        // 2. 실제

        // 3. 비교
    }
}