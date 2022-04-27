package com.smartf.comu.controller;

import com.smartf.comu.domain.Branch;
import com.smartf.comu.domain.Member;
import com.smartf.comu.dto.MemberInfoDto;
import com.smartf.comu.service.CompanyAdminService;
import com.smartf.comu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;
    private final CompanyAdminService companyAdminService;

    @Autowired
    public MemberController(MemberService memberService, CompanyAdminService companyAdminService) {
        this.memberService = memberService;
        this.companyAdminService = companyAdminService;
    }


    //슈퍼관리자만 가능
    @GetMapping("/members/all")
    @PreAuthorize("hasRole('SUPER')")
    public String listAll(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    // 직원 목록 페이지
    @GetMapping("/members")
    @PreAuthorize("hasAnyRole('BRANCH', 'ADMIN')")
    public String list(Model model, Authentication authentication) {

        if (authentication.getAuthorities().toString().equals("[ROLE_BRANCH]")) {
            List<Member> members = memberService.findMyBranchMembers();
            model.addAttribute("members", members);
            return "members/memberList";
        } else {
            List<Branch> branches = companyAdminService.getMyBranches();
            model.addAttribute("branches", branches);
            model.addAttribute("members", null);
            return "members/memberListSelect";
        }

    }


//    // 직원 추가 페이지
//    @GetMapping("/members/new")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String createForm(Model model) {
//        model.addAttribute("req", "new");
//        model.addAttribute("member", Member.builder().build());
//        return "members/createMemberForm";
//    }

    @GetMapping("/members/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String createForm(Model model) {
        List<Branch> branches = companyAdminService.getMyBranches();
        model.addAttribute("branches", branches);
        model.addAttribute("req", "new");
        model.addAttribute("member", Member.builder().build());
        return "members/createMemberSelectForm";
    }


//    // 직원 추가 처리
//    @PostMapping("/members/new")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String create(MemberInfoDto form) throws IOException {
//        memberService.addDriver(form);
//
//        return "redirect:/members";
//    }

    // 직원 추가 처리
    @PostMapping("/members/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String create(MemberInfoDto form) throws IOException {
            memberService.addDriver(form);


        return "redirect:/members";
    }


    // 직원 수정
    @GetMapping("/members/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public String updateMember(MemberInfoDto memberInfoDto) throws Exception {
        try {
            memberService.updateDriver(memberInfoDto);
        } catch (Exception e) {
            return "redirect:/members";
        }
        return "redirect:/members";
    }

    @GetMapping("/memberDelete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String carDelete(@PathVariable Long id) throws Exception {
        memberService.deleteMember(id);
        return "redirect:/members";
    }

    @ResponseBody
    @GetMapping("/members/branch/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Member> getMyMemberList(@PathVariable Long id) {
        return memberService.findBranchMembers(id);
    }



}

