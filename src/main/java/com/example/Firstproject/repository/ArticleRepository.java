package com.example.Firstproject.repository;

import com.example.Firstproject.entity.Article;
//curdrepository를 자동 임포트 해준다.
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
