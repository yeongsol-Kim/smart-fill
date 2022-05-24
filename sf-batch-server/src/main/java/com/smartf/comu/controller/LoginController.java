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
        String msg = "";
//        if (!exception.isEmpty()) {
//            switch (exception) {
//                case "00": msg = "아이디 또는 패스워드가 잘못되었습니다."; break;
//                case "01": msg = "계정이 비활성화 상태입니다."; break;
//                case "02": msg = "비밀번호가 만료되었습니다."; break;
//                case "03": msg = "비밀번호가 일치하지 않습니다."; break;
//                case "04": msg = "이메일이 잘못되었습니다."; break;
//                default: msg = "서버가 응답하지 않습니다. 잠시 후 다시 시도해주세요";
//            }
//        }
        model.addAttribute("error", error);
        model.addAttribute("exception", msg);

        return "loginPage";
    }


    @GetMapping("/register")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/register")
    public String signup(Model model, AdminDto adminDto) {

        try {
            adminService.addAdmin(adminDto);
        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("exception", e.getMessage());
            return "signup";
        }

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
