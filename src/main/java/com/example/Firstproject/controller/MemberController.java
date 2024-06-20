package com.example.Firstproject.controller;

import com.example.Firstproject.entity.Member;
import com.example.Firstproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.Firstproject.DTO.MemberForm;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members/new")
    public String newMemberForm(){

        return "members/new";
    }

    @PostMapping("/members/create")
    public String createMember(MemberForm form){
        // System.out.println(form.toString());
        // 1. DTO->엔티티로 변환
        Member member = form.toEntity();
        // System.out.println(member.toString());

        // 2. repository에 저장
        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());
        return "";
    }
}
