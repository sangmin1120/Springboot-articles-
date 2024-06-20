package com.example.Firstproject.repository;

import com.example.Firstproject.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,Long> {
}
