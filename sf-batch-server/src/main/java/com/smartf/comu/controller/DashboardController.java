package com.smartf.comu.controller;

import com.smartf.comu.domain.Branch;
import com.smartf.comu.domain.Log;
import com.smartf.comu.domain.Reservoir;
import com.smartf.comu.service.BranchService;
import com.smartf.comu.service.CompanyAdminService;
import com.smartf.comu.service.FillLogService;
import com.smartf.comu.service.ReservoirService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final BranchService branchService;
    private final CompanyAdminService companyAdminService;
    private final FillLogService fillLogService;
    private final ReservoirService reservoirService;

    public DashboardController(BranchService branchService, CompanyAdminService companyAdminService, FillLogService fillLogService, ReservoirService reservoirService) {
        this.branchService = branchService;
        this.companyAdminService = companyAdminService;
        this.fillLogService = fillLogService;
        this.reservoirService = reservoirService;
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
