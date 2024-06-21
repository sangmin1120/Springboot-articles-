package com.example.Firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 추가 어노테이션
@ToString
@Entity
public class Article {
    @Id // 엔티티의 대푯값 지정
    @GeneratedValue // 자동 생성 기능 추가 (숫자가 자동으로 매겨짐)
    private Long id;
    @Column // title 필드 선언 , DB 테이블의 title열과 연결됨
    private String title;
    @Column // content 필드 선언 , DB 테이블의 content열과 연결됨
    private String content;
}
