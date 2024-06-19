package com.example.Firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model; // model 클래스 패키지 자동 임포트

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username" , "상민");
        return "greeting"; // greetings.mutache 파일 변환
    }
}
