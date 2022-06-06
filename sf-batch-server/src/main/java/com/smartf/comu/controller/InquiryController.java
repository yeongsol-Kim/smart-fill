package com.smartf.comu.controller;

import com.smartf.comu.dto.InquiryDto;
import com.smartf.comu.dto.InquiryRequestDto;
import com.smartf.comu.entity.Inquiry;
import com.smartf.comu.entity.InquiryRequest;
import com.smartf.comu.service.InquiryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class InquiryController {
    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/inquiry")
    @PreAuthorize("hasRole('ADMIN')")
    public String inquiryPage(Model model, InquiryRequestDto inquiryRequestDto, Authentication authentication) {

        model.addAttribute("inquiries", inquiryService.getMyCompanyInquiry());
        System.out.println(inquiryService.getMyCompanyInquiry());
        return "inquiry/inquiryList";
    }

    @GetMapping("/inquiry/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String inquiryFormPage() {
        return "inquiry/inquiryForm";
    }

    @PostMapping("/inquiry/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String inquiryInsert(InquiryDto inquiryDto) {
        Inquiry inquiry = Inquiry.builder()
                .title(inquiryDto.getTitle())
                .contents(inquiryDto.getContents())
                .type(inquiryDto.getType())
                .inquiryTime(LocalDateTime.now())
                .build();
        inquiryService.insertMyInquiry(inquiry);
        return "redirect:/inquiry";
    }

    @PostMapping("/inquiry/request")
    @PreAuthorize("hasRole('SUPER')")
    public String inquiryRequest(InquiryRequestDto inquiryRequestDto) {
        InquiryRequest inquiryRequest = InquiryRequest.builder()
                .contents(inquiryRequestDto.getContents())
                //.inquiryId(inquiryRequestDto.getInquiryId())
                .requestDate(LocalDateTime.now())
                .build();
        inquiryService.insertRequest(inquiryRequest);
        return "redirect:/";
    }
}
