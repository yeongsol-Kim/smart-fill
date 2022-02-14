package com.smartf.comu.service;

import com.smartf.comu.domain.Member;
import com.smartf.comu.dto.MemberInfoDto;
import com.smartf.comu.repository.SpringDataJpaMemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
//@Service
public class MemberService implements UserDetailsService {

    private final SpringDataJpaMemberRepository memberRepository;

    public MemberService(SpringDataJpaMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long save(MemberInfoDto infoDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDto.setPassword(encoder.encode(infoDto.getPassword()));

        Member member = new Member();
        member.setUserName(infoDto.getUsername());
        member.setEmail(infoDto.getEmail());
        member.setAuth(infoDto.getAuth());
        member.setPassword("{bcrypt}" + infoDto.getPassword());

        return memberRepository.save(member).getId();
    }

    // 회원가입
    public Long join(Member member) {
        //중복 이름 회원 차단
        //validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

//    private void validateDuplicateMember(Member member) {
//        memberRepository.findByName(member.getUserName())
//                        .ifPresent(m -> {
//                            throw new IllegalStateException("이미 존재하는 회원입니다.");
//                        });
//    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 아이디로 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        return member;
    }
}
