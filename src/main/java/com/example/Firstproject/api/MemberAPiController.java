package com.example.Firstproject.api;

import com.example.Firstproject.DTO.MemberForm;
import com.example.Firstproject.entity.Member;
import com.example.Firstproject.repository.MemberRepository;
import com.example.Firstproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberAPiController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/api/members")
    public List<Member> index(){
        List<Member> memberList = memberService.index();
        return memberList;
    }
    @GetMapping("/api/members/{id}")
    public ResponseEntity<Member> show(@PathVariable Long id){
        Member member = memberService.show(id);
        // 3. 출력
        return (member!=null)?
                ResponseEntity.status(HttpStatus.OK).body(member):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/members")
    public ResponseEntity<Member> create(@RequestBody MemberForm dto){
        Member created  = memberService.create(dto);
        // 3. 반환
        return (created!=null)?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/members/{id}")
    public ResponseEntity<Member> update(@PathVariable Long id , @RequestBody MemberForm dto){
        Member updated = memberService.update(id,dto);
        return (updated!=null)?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/members/{id}")
    public ResponseEntity<Member> delete(@PathVariable Long id){
        Member deleted = memberService.deleted(id);
        // 3. 반환
        return (deleted != null)?
                ResponseEntity.status(HttpStatus.OK).body(deleted):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
