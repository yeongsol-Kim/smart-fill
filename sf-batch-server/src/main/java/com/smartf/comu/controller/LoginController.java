package com.smartf.comu.controller;

import com.smartf.comu.dto.MemberInfoDto;
import com.smartf.comu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private MemberService memberService;

    @Autowired
    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(value="error", required = false) String error, @RequestParam(value="exception", required = false) String exception) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "loginPage";
    }


    @GetMapping("/register")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/register")
    public String signup(MemberInfoDto infoDto) {
        memberService.save(infoDto);
        return "redirect:login";
    }

}
