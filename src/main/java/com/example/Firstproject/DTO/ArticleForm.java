package com.example.Firstproject.DTO;

import com.example.Firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title; //제목을 받을 필드
    private String content; // 내용을 받을 필드

    public Article toEntity() {
        return new Article(null , title , content);
    }
}
