package com.smartf.comu.controller;

import com.smartf.comu.domain.Pump;
import com.smartf.comu.domain.Reservoir;
import com.smartf.comu.dto.PumpDto;
import com.smartf.comu.service.PumpService;
import com.smartf.comu.service.ReservoirService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PumpController {

    private final PumpService pumpService;
    private final ReservoirService reservoirService;

    public PumpController(PumpService pumpService, ReservoirService reservoirService) {
        this.pumpService = pumpService;
        this.reservoirService = reservoirService;
    }

    @GetMapping("pumps")
    @PreAuthorize("hasRole('BRANCH')")
    public String pumpList(Model model) {
        List<Pump> pumps = pumpService.getMyPumps();
        model.addAttribute("pumps", pumps);
        return "pumps/pumpList";
    }

    @GetMapping("pumps/new")
    @PreAuthorize("hasRole('BRANCH')")
    public String addPumpForm(Model model) {
        List<Reservoir> reservoirs = reservoirService.getMyReservoirs();
        model.addAttribute("reservoirs", reservoirs);
        return "pumps/createPumpForm";
    }

    @PostMapping("pumps/new")
    @PreAuthorize("hasRole('BRANCH')")
    public String addPump(PumpDto pumpDto) {
        pumpService.addPump(pumpDto);
        return "redirect:/reservoirs";
    }
}
