package com.example.Firstproject.entity;

import com.example.Firstproject.DTO.ArticleForm;
import com.example.Firstproject.DTO.MemberForm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 추가 어노테이션
@ToString
@Getter
@Entity
public class Article {
    // 원래는 data.sql에서 id 값을 입력해 주었는데 새로운 article을 생성하면 기본키 error
    // 그래서 기본키를 DB에서 자동 생성하고 data.sql insert할 때 id 값을 없앴다.
    @Id // 엔티티의 대푯값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 기능 추가 (숫자가 자동으로 매겨짐)
    private Long id;
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;
    @Column // title 필드 선언 , DB 테이블의 title열과 연결됨
    private String title;
    @Column // content 필드 선언 , DB 테이블의 content열과 연결됨
    private String content;

    public void patch(Article article) {
        if (article.getTitle() != null) {
            this.title = article.getTitle();
        }
        if (article.getContent() != null) {
            this.content = article.getContent();
        }
    }
    public static Article toEntity(ArticleForm dto, Member member){
        return new Article(dto.getId(),
                member,
                dto.getTitle(),
                dto.getContent());
    }
}
