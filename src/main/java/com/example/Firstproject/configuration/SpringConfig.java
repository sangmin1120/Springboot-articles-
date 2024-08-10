package com.example.Firstproject.configuration;

import com.example.Firstproject.repository.ArticleRepository;
import com.example.Firstproject.repository.CommentRepository;
import com.example.Firstproject.repository.MemberRepository;
import com.example.Firstproject.service.ArticleService;
import com.example.Firstproject.service.CommentService;
import com.example.Firstproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository, ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.memberRepository = memberRepository;
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    @Bean
    public ArticleService ArticleService(ArticleRepository articleRepository, MemberRepository memberRepository){
        return new ArticleService(articleRepository , memberRepository);
    }

    @Bean
    public CommentService CommentService(CommentRepository commentRepository, ArticleRepository articleRepository){
        return new CommentService(commentRepository,articleRepository);
    }
}
