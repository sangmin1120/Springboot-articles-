package com.example.Firstproject.service;

import com.example.Firstproject.DTO.ArticleForm;
import com.example.Firstproject.DTO.CommentDto;
import com.example.Firstproject.entity.Article;
import com.example.Firstproject.entity.Member;
import com.example.Firstproject.repository.ArticleRepository;
import com.example.Firstproject.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository; // 게시글 리파지터리 객체 주입
    @Autowired
    private MemberRepository memberRepository;


    // REST의 GET
    public List<Article> index(Long memberId){
        List<Article> articleList = (List<Article>)articleRepository.findByMemberId(memberId);
        return articleList;
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // REST의 POST
    public Article create(Long memberId , ArticleForm dto) {
        Member member = memberRepository.findById(memberId).orElse(null);

        Article article = Article.toEntity(dto,member);
        log.info(article.toString());
        if (article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }

    // REST의 PATCH
    public Article updated(Long memberId , Long id, ArticleForm dto) {
        // patch : 아이디를 찾지 못해서 실패..!
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member==null){
            return null;
        }
        // DTO -> 엔티티
        Article article = Article.toEntity(dto,member);
        log.info("id: {} , article: {}",id,article.toString());

        // id로 타깃 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if (target == null || id != article.getId()){
            // 400 , 잘못된 요청 응답!
            log.info("잘못된 요청! id: {} , article: {}",id,article.toString());
            return null;
        }
        // 업테이트 및 정상 응답
        target.patch(article); // 일부 수정을 위해 메소드
        Article updated = articleRepository.save(target);
        return updated;
    }

    // REST의 DELETE
    public Article delete(Long memberId , Long id) {
        // id 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // target 조회
        if (target == null){
            return null;
        }
        // 성공적으로 삭제
        articleRepository.delete(target);
        return target;
    }

    public List<ArticleForm> articles(Long memberId){
        return articleRepository.findByMemberId(memberId)
                .stream().map(article -> ArticleForm.createArticleForm(article)).collect(Collectors.toList());
    }
    // transaction -test
//    @Transactional
//    public List<Article> createdList(List<ArticleForm> dtos) {
//        // dto 묶음을 엔티티 묶음으로 변환
//        List<Article> articleList = dtos.stream()
//                .map(dto -> dto.toEntity())
//                .collect(Collectors.toList());
//
//        // 엔티티 묶음을 DB에 저장
//        articleList.stream()
//                .map(article -> articleRepository.save(article));
//
//        // 강제 예외 발생
//        articleRepository.findById(-1L)
//                .orElseThrow(() -> new IllegalArgumentException("결제 실패!")); // 찾는 데이터가 없는 결제 실패
//
//        // 결과 값 반환
//        return articleList;
//    }
}
