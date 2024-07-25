package com.example.Firstproject.api;

import com.example.Firstproject.DTO.ArticleForm;
import com.example.Firstproject.entity.Article;
import com.example.Firstproject.repository.ArticleRepository;
import com.example.Firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService; // 서비스 객체 주입
//    @Autowired // 리파지토리 주입
//    private ArticleRepository articleRepository;
//
    // GET
    @GetMapping("/api/member/{memberId}/articles")
    public List<Article> index(@PathVariable Long memberId){
        return (List<Article>)articleService.index(memberId);
    }
    @GetMapping("/api/member/{memberId}/articles/{id}")
    public Article show(@PathVariable Long memberId , @PathVariable Long id){
        return articleService.show(memberId,id);
    }

    // POST
    @PostMapping("/api/member/{memberId}/articles")
    public ResponseEntity<Article> create(@PathVariable Long memberId,@RequestBody ArticleForm dto){
        Article created = articleService.create(memberId,dto);
        return (created != null)?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/api/member/{memberId}/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long memberId,@PathVariable Long id , @RequestBody ArticleForm dto){
        Article updated = articleService.updated(memberId,id,dto);
        return (updated != null)?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/member/{memberId}/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long memberId , @PathVariable Long id){
        Article deleted = articleService.delete(memberId , id);
        return (deleted != null)?
                ResponseEntity.status(HttpStatus.OK).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // transaction test
//    @PostMapping("/api/transaction-test")
//    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
//        List<Article> createdList = articleService.createdList(dtos);
//        return (createdList != null)?
//                ResponseEntity.status(HttpStatus.OK).body(createdList):
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
}
