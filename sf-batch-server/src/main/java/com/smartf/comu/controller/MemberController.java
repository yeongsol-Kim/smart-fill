package com.smartf.comu.controller;

import com.smartf.comu.domain.Member;
import com.smartf.comu.dto.MemberInfoDto;
import com.smartf.comu.service.MemberService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    //슈퍼관리자만 가능
    @GetMapping("/members/all")
    @PreAuthorize("hasRole('SUPER')")
    public String listAll(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    //로그인 회사 지점의 기사 리스트
    @GetMapping("/members")
    @PreAuthorize("hasRole('BRANCH')")
    public String list(Model model) {
        List<Member> members = memberService.findMyBranchMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }


    @GetMapping("/members/new")
    @PreAuthorize("hasRole('BRANCH')")
    public String createForm(Model model) {
        model.addAttribute("req", "new");
        model.addAttribute("member", Member.builder().build());
        return "members/createMemberForm";
    }



    @PostMapping("/members/new")
    @PreAuthorize("hasRole('BRANCH')")
    public String create(MemberInfoDto form) throws IOException {
        memberService.addDriver(form);


        return "redirect:/members";
    }

    @GetMapping("/members/update/{id}")
    @PreAuthorize("hasRole('BRANCH')")
    public String editForm(Model model, @PathVariable Long id) throws Exception {
        try {
            Member member = memberService.getEditMemberInfo(id);

            model.addAttribute("req", "update");
            model.addAttribute("id", id);
            model.addAttribute("member", member);

            return "members/createMemberForm";

        } catch (Exception e) {
            return "redirect:/members";
        }
    }

    @PostMapping("/members/update")
    @PreAuthorize("hasRole('BRANCH')")
    public String updateMember(MemberInfoDto memberInfoDto) throws Exception {
        try {
            memberService.updateDriver(memberInfoDto);
        } catch (Exception e) {
            return "redirect:/members";
        }
        return "redirect:/members";
    }

    @GetMapping("/memberDelete/{id}")
    @PreAuthorize("hasRole('BRANCH')")
    public String carDelete(@PathVariable Long id) throws Exception {
        memberService.deleteMember(id);
        return "redirect:/members";
    }



}

