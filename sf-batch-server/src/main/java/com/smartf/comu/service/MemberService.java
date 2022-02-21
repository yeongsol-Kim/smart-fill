package com.smartf.comu.service;

import com.smartf.comu.domain.Member;
import com.smartf.comu.dto.MemberInfoDto;
import com.smartf.comu.repository.SpringDataJpaMemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class MemberService implements UserDetailsService {

    private final SpringDataJpaMemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(SpringDataJpaMemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long save(MemberInfoDto infoDto) {

        infoDto.setPassword(passwordEncoder.encode(infoDto.getPassword()));

        Member member = Member.builder()
                .userName(infoDto.getUsername())
                .email(infoDto.getEmail())
                .auth(infoDto.getAuth())
                .password(infoDto.getPassword())
                .build();

        return memberRepository.save(member).getId();
    }

    // 기사 추가
    public Long addDriver(MemberInfoDto memberInfo) throws IOException {
        //중복 이름 회원 차단
        //validateDuplicateMember(member);
        Member member = Member.builder()
                .name(memberInfo.getName())
                .userName(memberInfo.getUsername())
                .email(memberInfo.getEmail())
                .phone_number(memberInfo.getPhoneNumber())
                .address(memberInfo.getAddress())
                .password(passwordEncoder.encode(memberInfo.getPassword()))
                .type(1)
                .datetime(OffsetDateTime.now())
                .activated(1l)
                .build();

        MultipartFile file = memberInfo.getFile();

        String dasePath = "C:/Users/W21236/Documents/boiler-plate/hello-spring/src/main/resources/static/uploadUserProfiles/"; //자신의 로컬 저장소
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));; // 파일 확장자
        String saveFileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")); // 저장할 파일 이름 (현재 시간)
        String downloadPath = dasePath + saveFileName + ext;

        file.transferTo(new File(downloadPath));

        member.setPicture(saveFileName + ext);

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserName(username);

        return member;
    }
}
