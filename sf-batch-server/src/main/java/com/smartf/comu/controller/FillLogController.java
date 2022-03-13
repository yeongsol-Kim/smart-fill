package com.smartf.comu.controller;

import com.smartf.comu.domain.Admin;
import com.smartf.comu.domain.Branch;
import com.smartf.comu.domain.Log;
import com.smartf.comu.domain.Reservoir;
import com.smartf.comu.dto.ReservoirDto;
import com.smartf.comu.service.CompanyAdminService;
import com.smartf.comu.service.FillLogService;
import com.smartf.comu.service.ReservoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FillLogController {
    private final FillLogService fillLogService;
    private final ReservoirService reservoirService;
    private final CompanyAdminService companyAdminService;

    @Autowired
    public FillLogController(FillLogService fillLogService, ReservoirService reservoirService, CompanyAdminService companyAdminService) {
        this.fillLogService = fillLogService;
        this.reservoirService = reservoirService;
        this.companyAdminService = companyAdminService;
    }


    @GetMapping("/")
    public String dashboard(Model model, Authentication authentication) {
        if (authentication.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
            List<Branch> branches = companyAdminService.getMyBranches();
            model.addAttribute("branches", branches);
            return "company/demo";
        } else {
            List<Log> fillLogs = fillLogService.getMyBranchLogs();
            List<Reservoir> reservoirs = reservoirService.getMyReservoirs();
            if (reservoirs.isEmpty()) {
                reservoirs = null;
            }
            model.addAttribute("fillLogs", fillLogs);
            model.addAttribute("reservoirs", reservoirs);
            return "dashboard/register_place";
        }

    }


    // 미완성---
    @GetMapping("/dashboard/{branchId}")
    public String dashboardById(@PathVariable Long id, Model model) {
        List<Log> fillLogs = fillLogService.getLogsByBranchId(id);
        List<Reservoir> reservoirs = reservoirService.getMyReservoirs();
        model.addAttribute("fillLogs", fillLogs);
        model.addAttribute("reservoirs", reservoirs);
        return "dashboard/register_place";
    }

}
