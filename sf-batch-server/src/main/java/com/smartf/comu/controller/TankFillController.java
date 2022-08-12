package com.smartf.comu.controller;

import com.smartf.comu.dto.TankFillDto;
import com.smartf.comu.dto.form.TankFillAddForm;
import com.smartf.comu.service.TankFillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TankFillController {

    private final TankFillService tankFillService;

    // 입고 정보 등록 페이지
    @GetMapping("/tank-fills/add")
    public String addTankFillIdForm(Model model) {
        model.addAttribute("tankFill", new TankFillAddForm());
        return "tankFill/addIdForm";
    }

    // 입고 정보 등록
    @PostMapping("/tank-fills/add")
    public String addTankFillId(@Validated @ModelAttribute("tankFill") TankFillAddForm form, BindingResult bindingResult) {

        // validation 검증 실패
        if (bindingResult.hasErrors()) {
            return "tankFill/addIdForm";
        }

        // dto로 변환 + 저장
        TankFillDto tankFillDto = TankFillDto.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .amount(form.getAmount())
                .build();
        Long id = tankFillService.saveTankFill(tankFillDto);


        return "redirect:/tank-fills";
    }

}
