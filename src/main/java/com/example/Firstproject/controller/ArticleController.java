package com.example.Firstproject.controller;

import com.example.Firstproject.DTO.CommentDto;
import com.example.Firstproject.entity.Article;
import com.example.Firstproject.entity.Member;
import com.example.Firstproject.repository.ArticleRepository;
import com.example.Firstproject.DTO.ArticleForm;
import com.example.Firstproject.repository.MemberRepository;
import com.example.Firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static org.yaml.snakeyaml.events.Event.ID.Comment;

@Slf4j // 로깅 기능을 위한 어노테이션 추가
@Controller
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해 놓은 리파지토리 객체 주입(DI : dependenct injection 의존성 주입)
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }

    @PostMapping("/articles/create") // mustache에서 action과 같
    public String createArticle(ArticleForm form){
        // System.out.println(form.toString());
        // 1. DTO를 엔티티로 변환
        Long member_id = form.getMemberId();
        Member member = memberRepository.findById(member_id).orElse(null);

        Article article = Article.toEntity(form , member);
        // log.info(article.toString());

        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}") // 데이터 조회 요청 접수
    public String show(@PathVariable Long id , Model model){ // 매개변수로 id 받아 오기
        log.info("id = " +id); // id를 잘 받았는 지 확인
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);

        // 2. 모델에 데이터 등록하기
        model.addAttribute("article" , articleEntity);
        model.addAttribute("commentDtos",commentDtos);

        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 데이터 가져오기
        List<Article> articleEntityList = (List<Article>) articleRepository.findAll();

        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList" , articleEntityList);

        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id , Model model){
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 등록하기
        model.addAttribute("article",articleEntity);

        // 뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){ // form을 DTO로 가져와
        log.info(form.toString());
        Long member_id = form.getMemberId();
        Member member = memberRepository.findById(member_id).orElse(null);

        // 1. 엔티티 형태로 변경
        Article articleEntity = Article.toEntity(form,member);

        // 2. 엔티티 저장
        // 2.1 DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2.2 기존 데이터 값을 갱신
        if (target != null){
            articleRepository.save(articleEntity); // 엔티티를 DB에 갱신
        }

        // 3. 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id , RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다");
        // 1. Id 찾기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2. DB에서 삭제
        if (target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제됐습니다!");
        }

        // 3. redirect
        return "redirect:/articles";
    }
}
