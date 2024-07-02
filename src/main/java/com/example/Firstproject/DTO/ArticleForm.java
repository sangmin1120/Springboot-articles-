package com.example.Firstproject.DTO;

import com.example.Firstproject.entity.Article;
import com.example.Firstproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class ArticleForm {
    private Long id; // update할 때 id를 히든으로 가져와서 추가함
    private Long memberId;
    private String title; //제목을 받을 필드
    private String content; // 내용을 받을 필드

}
