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
                .username(infoDto.getUsername())
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
                .username(memberInfo.getUsername())
                .password(passwordEncoder.encode(memberInfo.getPassword()))
                .name(memberInfo.getName())
                .email(memberInfo.getEmail())
                .phoneNumber(memberInfo.getPhoneNumber())
                .address(memberInfo.getAddress())
                .datetime(OffsetDateTime.now())
//                .branchId(SecurityUtil.getCurrentDependentId().orElse(null))
                .branchId(memberInfo.getBranchId())
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

    public void updateDriver(MemberInfoDto memberInfo) throws Exception {
        // 올바른 접근인지 검사
        Member member = isMyBranchMember(memberInfo.getId());

        // 입력값들 업데이트
        member.setUsername(memberInfo.getUsername());
        member.setName(memberInfo.getName());
        member.setEmail(memberInfo.getEmail());
        member.setPhoneNumber(memberInfo.getPhoneNumber());
        member.setAddress(memberInfo.getAddress());

        //비밀번호는 빈칸일 경우에는 유지
        if (!memberInfo.getPassword().isBlank()) {
            member.setPassword(passwordEncoder.encode(memberInfo.getPassword()));
        }

        memberRepository.save(member);
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

    // 검색 회사 회원 조회
    public List<Member> findBranchMembers(Long branchId) {
        return memberRepository.findByBranchId(branchId);
    }

    public Member getEditMemberInfo(Long memberId) throws Exception {
        // 올바른 접근인지 검사
        Member member = isMyBranchMember(memberId);

        return member;

    }

    // 회원 아이디로 조회
    public Optional<Member> getMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public void deleteMember(Long id) throws Exception {
        // 올바른 접근인지 검사
        Member member = isMyBranchMember(id);

        memberRepository.deleteById(id);
    }



    private Member isMyBranchMember(Long id) throws Exception {
        Optional<Member> member = getMemberById(id);

        // 잘못된 uri (없는 직원)일 때 오류 발생
        member.orElseThrow(() -> new Exception("member is null"));

        // 다른 회사의 직원을 수정하려고 할 때 오류 발생
//        if(member.get().getBranchId() != SecurityUtil.getCurrentDependentId().orElse(null)) {
//            throw new Exception("not branch member");
//        }

        return member.get();
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Member member = memberRepository.findByUserName(username);
//
//        return member;
//    }
}
