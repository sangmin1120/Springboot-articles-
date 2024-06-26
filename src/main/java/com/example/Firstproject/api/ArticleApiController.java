package com.example.Firstproject.api;

import com.example.Firstproject.DTO.ArticleForm;
import com.example.Firstproject.entity.Article;
import com.example.Firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired // 리파지토리 주입
    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return (List<Article>)articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id , @RequestBody ArticleForm dto){
        // DTO -> 엔티티
        Article article = dto.toEntity();
        log.info("id: {} , article: {}",id,article.toString());

        // id로 타깃 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if (target == null || id != article.getId()){
            // 400 , 잘못된 요청 응답!
            log.info("잘못된 요청! id: {} , article: {}",id,article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 업테이트 및 정상 응답
        target.patch(article); // 일부 수정을 위해 메소드
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        // id 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // target 조회
        if (target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 성공적으로 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
