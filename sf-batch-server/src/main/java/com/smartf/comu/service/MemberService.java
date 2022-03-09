package com.smartf.comu.service;

import com.smartf.comu.domain.Authority;
import com.smartf.comu.domain.Member;
import com.smartf.comu.domain.UserAuthority;
import com.smartf.comu.dto.MemberInfoDto;
import com.smartf.comu.repository.SpringDataJpaMemberRepository;
import com.smartf.comu.repository.UserAuthorityRepository;
import com.smartf.comu.util.SecurityUtil;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class MemberService {

    private final SpringDataJpaMemberRepository memberRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(SpringDataJpaMemberRepository memberRepository, UserAuthorityRepository userAuthorityRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.userAuthorityRepository = userAuthorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long save(MemberInfoDto infoDto) {

        infoDto.setPassword(passwordEncoder.encode(infoDto.getPassword()));

        Member member = Member.builder()
                .userName(infoDto.getUsername())
                .email(infoDto.getEmail())
                .password(infoDto.getPassword())
                .build();

        UserAuthority userAuthority = UserAuthority.builder()
                .userId(memberRepository.save(member).getId())
                .authorityName("ROLE_ADMIN")
                .build();

        userAuthorityRepository.save(userAuthority);


        return memberRepository.save(member).getId();
    }

    // 기사 추가
    public Long addDriver(MemberInfoDto memberInfo) throws IOException {
        //중복 이름 회원 차단
        //validateDuplicateMember(member);

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()

                .userName(memberInfo.getUsername())
                .password(passwordEncoder.encode(memberInfo.getPassword()))
                .name(memberInfo.getName())
                .email(memberInfo.getEmail())
                .phone_number(memberInfo.getPhoneNumber())
                .address(memberInfo.getAddress())
                .datetime(OffsetDateTime.now())
                .branchId(SecurityUtil.getCurrentDependentId().orElse(null))
                .authorities(Collections.singleton(authority))
                .activated(1l)
                .build();

        try {
            MultipartFile file = memberInfo.getFile();

            String dasePath = "C:/Users/W21236/Documents/boiler-plate/hello-spring/src/main/resources/static/uploadUserProfiles/"; //자신의 로컬 저장소
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));; // 파일 확장자
            String saveFileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")); // 저장할 파일 이름 (현재 시간)
            String downloadPath = dasePath + saveFileName + ext;

            file.transferTo(new File(downloadPath));

            member.setPicture(saveFileName + ext);
        } catch (Exception e) {
            member.setPicture("");
        }


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

    // 해당 회사 회원 조회
    public List<Member> findMyBranchMembers() {
        Long id = SecurityUtil.getCurrentDependentId().orElse(null);
        return memberRepository.findByBranchId(id);
    }

    // 회원 아이디로 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Member member = memberRepository.findByUserName(username);
//
//        return member;
//    }
}
