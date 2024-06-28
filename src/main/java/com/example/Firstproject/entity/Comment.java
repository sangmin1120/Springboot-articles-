package com.example.Firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 1씩 증가
    private Long id; // 대표키
    @ManyToOne // 댓글과 게시글은 다:1 관계로 설정
    @JoinColumn(name="article_id") // 외래키 생성 , Article 엔티티의 기본키(id)와 매핑
    private Article article; // 해당 댓글의 부모 게시글
    @Column
    private String nickname; // 댓글 단 사람
    @Column
    private String body; // 댓글 본문

}
