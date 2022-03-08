package com.smartf.comu.controller;

import com.smartf.comu.dto.AdminDto;
import com.smartf.comu.dto.MemberInfoDto;
import com.smartf.comu.service.AdminService;
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
    private final AdminService adminService;

    @Autowired
    public LoginController(MemberService memberService, AdminService adminService) {
        this.memberService = memberService;
        this.adminService = adminService;
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
    public String signup(AdminDto adminDto) {
        adminService.addAdmin(adminDto);
        return "redirect:login";
    }

    @PostMapping("/branchAdmin/new")
    public String addBranchAdmin(AdminDto adminDto) {
        adminService.addBranchAdmin(adminDto);
        return  "redirect:/";
    }

    @PostMapping("/registermem")
    public String signupMember(MemberInfoDto infoDto) {
        memberService.save(infoDto);
        return "redirect:login";
    }

}
