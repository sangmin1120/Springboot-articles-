package com.example.Firstproject.service;

import com.example.Firstproject.DTO.ArticleForm;
import com.example.Firstproject.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test; // Test 패키지 임포트
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*; // 앞으로 사용할 수 있는 패키지 임포트

@SpringBootTest // 해당 클래스를 스프링 부트와 연동해 테스트
class ArticleServiceTest {
    @Autowired
    ArticleService articleService; // 객체 주입

    @Test // 해당 매서드가 테스트 코드임을 선언
    void index() {
        // 1. 예상 데이터
        Article a = new Article(1L,"가가가가","1111");
        Article b = new Article(2L,"나나나나","2222");
        Article c = new Article(3L,"다다다다","3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        // 2. 실제 데이터
        List<Article> articles = articleService.index();

        // 3. 비교 검증
        assertEquals(expected.toString() , articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        // 1. 예상
        Long id = 1L;
        Article expected = articleService.show(id);

        // 2. 실제
        Article article = articleService.show(id);

        // 3. 비교
        assertEquals(expected.toString() , article.toString());
    }
    @Test
    void show_실패_존재하지_않는_id_입력() {
        // 1. 예상
        Long id = 4L;
        Article expected = null;

        // 2. 실제
        Article article = articleService.show(id);

        // 3. 비교
        assertEquals(expected , article);
    }

    @Test
    @Transactional // 테스트가 끝나면 데이터를 처음으로 되돌려야한다.
    void create_성공_title과_content만_있는_dto_입력() {
        // 1. 예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null,title,content);
        Article expected = new Article(4L,title,content);

        // 2. 실제
        Article article = articleService.create(dto);

        // 3. 비교
        assertEquals(expected.toString() , article.toString());

    }
    @Test
    @Transactional // 테스트가 끝나면 데이터를 처음으로 되돌려야한다.
    void create_실패_id가_포함된_dto_입력() {
        // 1. 예상
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = null;

        // 2. 실제
        Article article = articleService.create(dto);

        // 3. 비교
        assertEquals(expected , article);
    }

    @Test
    @Transactional
    void update_성공_id와_title_content가_있는_dto_입력(){
        // 1. 예상
        Long id = 2L;
        String title = "나다라마";
        String content = "2345";
        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = new Article(id,title,content);

        // 2. 실제
        Article article = articleService.updated(id,dto);

        // 3. 비교
        assertEquals(expected.toString() , article.toString());
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title만_있는_dto_입력(){
        // 1. 예상
        Long id = 2L;
        String title = "나다라마";
        String content = null;
        String content_real = articleService.show(id).getContent();

        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = new Article(id,title,content_real);

        // 2. 실제
        Article article = articleService.updated(id,dto);

        // 3. 비교
        assertEquals(expected.toString() , article.toString());
    }

    @Test
    @Transactional
    void update_실패_존재하지_않는_id의_dto_입력(){
        // 1. 예상
        Long id = 4l;
        String title = null;
        String content = null;
        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = null;

        // 2. 실제
        Article article = articleService.updated(id,dto);

        // 3. 비교
        assertEquals(expected , article);
    }

    @Test
    @Transactional
    void delete_성공_존재하는_id_입력(){
        // 1. 예상
        Long id = 1L;
        Article expected = articleService.show(id);

        // 2. 실제
        Article article = articleService.delete(id);

        // 3. 비교
        assertEquals(expected.toString() , article.toString());
    }

    @Test
    @Transactional
    void delete_실패_존재하지_않는_id_입력(){
        // 1. 예상
        Long id = 4L;
        Article expeceted = null;

        // 2. 실제
        Article article = articleService.delete(id);

        // 3. 비교
        assertEquals(expeceted , article);
    }
}