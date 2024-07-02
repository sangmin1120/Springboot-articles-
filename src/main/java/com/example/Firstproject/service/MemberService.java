package com.example.Firstproject.service;

import com.example.Firstproject.DTO.MemberForm;
import com.example.Firstproject.entity.Member;
import com.example.Firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;


    public List<Member> index() {
        return (List<Member>)memberRepository.findAll();
    }

    public Member show(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member create(MemberForm dto) {
           Member member = dto.toEntity();
           Member created = memberRepository.save(member);
           return created;
    }

    public Member update(Long id, MemberForm dto) {
        Member target = memberRepository.findById(id).orElse(null);

        if (target == null){
            log.info("댓글 수정 실패");
            return null;
        }

        target.patch(dto);

        Member updated = memberRepository.save(target);
        return updated;
    }
    public Member deleted(Long id) {
        Member target = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("멤버를 못 찾았습니다"));

        memberRepository.delete(target);
        return target;
    }
}
