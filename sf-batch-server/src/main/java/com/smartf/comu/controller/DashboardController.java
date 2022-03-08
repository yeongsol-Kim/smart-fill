package com.smartf.comu.controller;

import com.smartf.comu.domain.Branch;
import com.smartf.comu.service.BranchService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {

    private final BranchService branchService;

    public DashboardController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/branch/new")
    public String addBranch(String name) {
        branchService.addMyCompanyBranch(name);
        return "redirect:/";
    }

    @GetMapping("/das")
    public String dashboard(Model model) {
        return "dashboard/register_place";
    }

    @GetMapping("/super")
    public String superDash(Model model) {
        return "super/register_place";
    }

}
