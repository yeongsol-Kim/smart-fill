package com.smartf.comu.controller;

import com.smartf.comu.domain.Reservoir;
import com.smartf.comu.dto.ReservoirDto;
import com.smartf.comu.service.ReservoirService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReservoirController {

    private final ReservoirService reservoirService;

    public ReservoirController(ReservoirService reservoirService) {
        this.reservoirService = reservoirService;
    }

    @GetMapping("/reservoirs")
    @PreAuthorize("hasRole('BRANCH')")
    public String reservoirList(Model model) {
        List<Reservoir> reservoirs = reservoirService.getMyReservoirs();
        model.addAttribute("reservoirs", reservoirs);
        return "reservoirs/reservoirList";
    }

    @GetMapping("/reservoirs/new")
    @PreAuthorize("hasRole('BRANCH')")
    public String createReservoirForm() {
        return "reservoirs/reservoirCreateForm";
    }

    @PostMapping("/reservoirs/new")
    @PreAuthorize("hasRole('BRANCH')")
    public String createReservoir(ReservoirDto reservoirDto) {
        reservoirService.addReservoir(reservoirDto);
        return "redirect:/reservoirs";
    }

    @PostMapping("/reservoirs/put")
    @PreAuthorize("hasRole('BRANCH')")
    public String reservoirPut(ReservoirDto reservoirDto) {
        reservoirService.putReservoir(reservoirDto);
        return "redirect:/";
    }
}
