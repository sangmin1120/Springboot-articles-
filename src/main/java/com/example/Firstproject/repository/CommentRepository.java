package com.example.Firstproject.repository;

import com.example.Firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 모든 댓글 조회
    @Query(value="select * from comment where article_id = :articleId" , nativeQuery = true)
    List<Comment> findByArticleId(String articleId);

    // 특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(String nickname); // 이 메서드를 수행할 쿼리를 orm.xml에 작성
}
