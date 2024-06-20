package com.example.Firstproject.controller;

import com.example.Firstproject.DTO.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }

    @PostMapping("/articles/create") // mustache에서 action과 같
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());
        return "";
    }
}
