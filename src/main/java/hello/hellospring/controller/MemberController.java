package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.dto.MemberInfoDto;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String create(MemberForm form) throws IOException {
        Member member = new Member();
        member.setUserName(form.getName());
        member.setEmail(form.getEmail());
        member.setPhone_number(form.getPhone_number());
        member.setAddress(form.getAddress());

        MultipartFile file = form.getFile();

        String dasePath = "C:/Users/W21236/Documents/boiler-plate/hello-spring/src/main/resources/static/uploadUserProfiles/"; //자신의 로컬 저장소
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));; // 파일 확장자
        String saveFileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")); // 저장할 파일 이름 (현재 시간)
        String downloadPath = dasePath + saveFileName + ext;

        file.transferTo(new File(downloadPath));

        member.setPicture(saveFileName + ext);

        memberService.join(member);

        return "redirect:/members";
    }

    @GetMapping("/memberDelete/{id}")
    public String carDelete(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "redirect:/members";
    }



}

