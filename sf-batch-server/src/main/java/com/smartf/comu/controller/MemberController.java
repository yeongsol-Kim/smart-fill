package com.smartf.comu.controller;

import com.smartf.comu.domain.Member;
import com.smartf.comu.dto.MemberInfoDto;
import com.smartf.comu.service.MemberService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }


    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }



    @PostMapping("/members/new")
    public String create(MemberInfoDto form) throws IOException {

        memberService.addDriver(form);


        return "redirect:/members";
    }

    @GetMapping("/memberDelete/{id}")
    public String carDelete(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "redirect:/members";
    }



}

