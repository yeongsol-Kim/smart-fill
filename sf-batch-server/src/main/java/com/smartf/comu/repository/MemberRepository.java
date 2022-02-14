package com.smartf.comu.repository;

import com.smartf.comu.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByEmail(String email);
    List<Member> findAll();
}
