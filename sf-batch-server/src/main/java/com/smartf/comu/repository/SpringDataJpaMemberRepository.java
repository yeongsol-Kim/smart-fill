package com.smartf.comu.repository;

import com.smartf.comu.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> {

    Member findByUserName(String aString);
    Member findByEmail(String email);

}
