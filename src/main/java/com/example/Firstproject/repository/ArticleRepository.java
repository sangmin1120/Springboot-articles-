package com.example.Firstproject.repository;

import com.example.Firstproject.entity.Article;
//curdrepository를 자동 임포트 해준다.
import com.example.Firstproject.entity.Comment;
import com.example.Firstproject.entity.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    @Query(value="select * from article where member_id = :memberId" , nativeQuery = true)
    List<Article> findByMemberId(Long memberId);
}
