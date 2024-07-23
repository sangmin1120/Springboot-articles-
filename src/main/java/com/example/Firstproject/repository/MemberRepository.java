package com.example.Firstproject.repository;

import com.example.Firstproject.entity.Comment;
import com.example.Firstproject.entity.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member,Long> {

    // 이메일로 조회
    @Query(value="select * from member where email = :input_email" , nativeQuery = true)
    Member findByEamil(String input_email);
}
