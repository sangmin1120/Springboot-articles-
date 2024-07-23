package com.example.Firstproject.controller;

import com.example.Firstproject.entity.Member;
import com.example.Firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.Firstproject.DTO.MemberForm;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder; // DI

    @GetMapping("/members/new")
    public String newMemberForm(){

        return "members/new";
    }

    @PostMapping("/members/create")
    public String createMember(MemberForm form){
        // System.out.println(form.toString());
        /* 비빌번호 인코딩 */
        String encodedPwd = passwordEncoder.encode(form.getPassword());
        // 1. DTO->엔티티로 변환
        Member member = new Member(null,form.getEmail(),encodedPwd);
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

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id , Model model){
        // 엔티티 찾기
        Member memberEntity = memberRepository.findById(id).orElse(null);

        log.info(memberEntity.toString());
        // 모델 등록
        model.addAttribute("member",memberEntity);

        // 뷰 반환
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form){
        log.info(form.toString());

        // 엔티티로 변환
        Member memberEntity = form.toEntity();

        // DB에서 찾기
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        // 갱신
        if (target != null){
            memberRepository.save(memberEntity);
        }
        // 리다이렉트
        return "redirect:/members/"+memberEntity.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delte(@PathVariable Long id , RedirectAttributes rttr){
        // 1. id로 DB에서 찾아
        Member target = memberRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2. DB에서 삭제
        if (target != null){
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제되었습니다");
        }

        // 3. 뷰 템플릿 반환
        return "redirect:/members";
    }

    // init 화면에서 로그인 encoding된 로그인
    @GetMapping("/login")
    public String show_login(){
        return "members/init";
    }

    @GetMapping("/members/check")
    public String check_id(MemberForm dto , RedirectAttributes rttr){
        // 1. form을 받아와 엔티티로 변경 후
        Member entity = dto.toEntity();
        // log.info(entity.toString());

        // 2. DB에서 아이디 값을 찾아
        Member target = memberRepository.findByEamil(entity.getEmail());
        // log.info(target.toString());

        // 3. 비교
        if (target == null){
            // 실패 모달 띄우고 리다이렉트
            rttr.addFlashAttribute("msg","target 찾기 실패..!");
            return "redirect:/login";
        }
        if ((entity.getEmail() == target.getEmail()) && (entity.getPassword() != entity.getPassword())){
            // 실패 화면 띄우고 리다이렉트
            rttr.addFlashAttribute("msg","불일치..!");
            return "redirect:/login";
        }

        // 4. 로그인 성공하면 "redirect:/members";
        return "members/mypage";
    }
}
