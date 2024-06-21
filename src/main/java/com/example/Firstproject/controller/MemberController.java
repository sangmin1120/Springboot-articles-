package com.example.Firstproject.controller;

import com.example.Firstproject.entity.Member;
import com.example.Firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.Firstproject.DTO.MemberForm;

import java.util.List;

@Slf4j
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
        log.info(saved.toString());
        return "redirect:/members/"+saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id , Model model){
        log.info("member.id ="+id);
        // 1. id 조회
        Member memberEntity = memberRepository.findById(id).orElse(null);
        // 2. 모델에 적용
        model.addAttribute("member",memberEntity);
        // 3. 뷰 페이지 반환
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        // 1. 모든 엔티티 반환
        List<Member> memberEntityList = (List<Member>) memberRepository.findAll();
        // 2. 모델 적용
        model.addAttribute("memberList", memberEntityList);
        // 3. 뷰 페이지 반환
        return "members/index";
    }
}
