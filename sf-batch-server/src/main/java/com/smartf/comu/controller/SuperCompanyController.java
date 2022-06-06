package com.smartf.comu.controller;

import com.smartf.comu.domain.Branch;
import com.smartf.comu.entity.Inquiry;
import com.smartf.comu.entity.Company;
import com.smartf.comu.service.CompanyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SuperCompanyController {

    private final CompanyService companyService;

    public SuperCompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    // 거래처 목록 페이지
    @GetMapping("/company")
    @PreAuthorize("hasRole('SUPER')")
    public String companyListPage(Model model) {

        // 거레처 리스트
        List<Company> companies = companyService.findAllCompany();
        model.addAttribute("companies", companyService.findAllCompany());

//        for (Company c : companies) {
//            c.getId()
//        }

        return "super/company/companyList";
    }


    //거래처 둥록 페이지
    @GetMapping("/company/new")
    @PreAuthorize("hasRole('SUPER')")
    public String companyFormPage() {

        return "super/company/companyForm";
    }

    //거래처 둥록 처리
    @PostMapping("/company/new")
    @PreAuthorize("hasRole('SUPER')")
    public String companyInsert(Company company) {
        companyService.insertCompany(company);

        return "redirect:/company";
    }

    //거래처 상세 페이지
    @GetMapping("/company/{id}")
    @PreAuthorize("hasRole('SUPER')")
    public String companyDetailPage(@PathVariable Long id, Model model) {

        // 거래처 정보
        Company company = companyService.getCompanyInfo(id).orElse(null);
        model.addAttribute("company", company);

        // 거래처의 지점 정보
        List<Branch> branches = companyService.getBranches(id);
        model.addAttribute("branches", branches);

        // 거래처의 문의 내역
        List<Inquiry> inquiries = companyService.getInquiries(id);
        model.addAttribute("inquiries", inquiries);

        return "super/company/company";
    }

    @PostMapping("/company/status")
    @PreAuthorize("hasRole('SUPER')")
    public String updateCompanyStatus(@RequestParam Long id, @RequestParam int status) {

        companyService.setStatus(id, status);

        return "redirect:/company/" + id;
    }
}
