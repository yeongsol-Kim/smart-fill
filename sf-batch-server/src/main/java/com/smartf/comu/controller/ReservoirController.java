package com.smartf.comu.controller;

import com.smartf.comu.domain.Pump;
import com.smartf.comu.domain.Reservoir;
import com.smartf.comu.dto.ReservoirDto;
import com.smartf.comu.service.BranchService;
import com.smartf.comu.service.PumpService;
import com.smartf.comu.service.ReservoirService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ReservoirController {

    private final ReservoirService reservoirService;
    private final BranchService branchService;
    private final PumpService pumpService;

    public ReservoirController(ReservoirService reservoirService, BranchService branchService, PumpService pumpService) {
        this.reservoirService = reservoirService;
        this.branchService = branchService;
        this.pumpService = pumpService;
    }

    // 저장조 목록 페이지
    @GetMapping("/reservoirs")
    @PreAuthorize("hasRole('BRANCH')")
    public String reservoirList(Model model) {
        List<Reservoir> reservoirs = reservoirService.getMyReservoirs();
        List<Pump> pumps = pumpService.getMyPumps();
        model.addAttribute("pumps", pumps);
        model.addAttribute("reservoirs", reservoirs);
        return "reservoirs/reservoirList";
    }

    // 저장조 등록 페이지
    @GetMapping("/reservoirs/new")
    @PreAuthorize("hasRole('BRANCH')")
    public String createReservoirForm() {
        return "reservoirs/reservoirCreateForm";
    }

    // 저장조 등록 처리
    @PostMapping("/reservoirs/new")
    @PreAuthorize("hasRole('BRANCH')")
    public String createReservoir(ReservoirDto reservoirDto) {
        reservoirService.addReservoir(reservoirDto);
        return "redirect:/reservoirs";
    }

    // 입고 처리
    @PostMapping("/reservoirs/put")
    @PreAuthorize("hasAnyRole('BRANCH', 'ADMIN')")
    public String reservoirPut(HttpServletRequest request, ReservoirDto reservoirDto) {
//        reservoirDto.getId()
//        branchService.isMyBranch(reservoirDto.getAmount())

        reservoirService.putReservoir(reservoirDto);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
