package com.smartf.comu.controller;

import com.smartf.comu.domain.Branch;
import com.smartf.comu.domain.Car;
import com.smartf.comu.domain.Log;
import com.smartf.comu.domain.Reservoir;
import com.smartf.comu.dto.BranchDto;
import com.smartf.comu.dto.LogReportDto;
import com.smartf.comu.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {

    private final BranchService branchService;
    private final CompanyAdminService companyAdminService;
    private final FillLogService fillLogService;
    private final ReservoirService reservoirService;
    private final CarService carService;

    public DashboardController(BranchService branchService, CompanyAdminService companyAdminService, FillLogService fillLogService, ReservoirService reservoirService, CarService carService) {
        this.branchService = branchService;
        this.companyAdminService = companyAdminService;
        this.fillLogService = fillLogService;
        this.reservoirService = reservoirService;
        this.carService = carService;
    }

    @GetMapping("/")
    public String dashboard(Model model, Authentication authentication) {
        String authType = authentication.getAuthorities().toString();
        if (authType.equals("[ROLE_ADMIN]")) {

            List<BranchDto> branches = companyAdminService.getMyBranches();
            model.addAttribute("branches", branches);
            return "company/demo";

        } else if (authType.equals("[ROLE_BRANCH]")) {
            List<Log> fillLogs = fillLogService.getMyBranchLogs();
            List<Reservoir> reservoirs = reservoirService.getMyReservoirs();
            List<LogReportDto> logReports = fillLogService.getMyGraphData();
            List<Car> cars = carService.getMyBranchCarList();
            List<String> months = new ArrayList<>();
            List<Long> amounts = new ArrayList<>();

            // 프론트 처리를 위해 값이 없으면 null 주입
            if (reservoirs.isEmpty()) {
                reservoirs = null;
            }

            if (cars.isEmpty()) {
                cars = null;
            }

            logReports.stream().forEach(s -> {
                months.add(s.getMonth() + "월");
                amounts.add(s.getSumAmount());
            });

            model.addAttribute("fillLogs", fillLogs);
            model.addAttribute("reservoirs", reservoirs);
            model.addAttribute("cars", cars);
            model.addAttribute("months", months);
            model.addAttribute("amounts", amounts);
            return "dashboard/branch";
        } else if(authType.equals("[ROLE_SUPER]")) {
            return "super/dash";
        }
        return "loginPage";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String dashboard(@PathVariable Long id, Model model, Authentication authentication) throws Exception {

        // 접근 제어 (본인 회사 소속 지점인지 검사)
        if(!branchService.isMyBranch(id)) {
            return "redirect:/";
        }

        List<Log> fillLogs = fillLogService.getBranchLogs(id);
        List<Reservoir> reservoirs = reservoirService.getReservoirs(id);
        List<LogReportDto> logReports = fillLogService.getGraphData(id);
        List<Car> cars = carService.getBranchCarList(id);
        List<String> months = new ArrayList<>();
        List<Long> amounts = new ArrayList<>();

        // 프론트 처리를 위해 값이 없으면 null 주입
        if (reservoirs.isEmpty()) {
            reservoirs = null;
        }

        if (cars.isEmpty()) {
            cars = null;
        }

        logReports.stream().forEach(s -> {
            months.add(s.getMonth() + "월");
            amounts.add(s.getSumAmount());
        });

        model.addAttribute("fillLogs", fillLogs);
        model.addAttribute("reservoirs", reservoirs);
        model.addAttribute("cars", cars);
        model.addAttribute("months", months);
        model.addAttribute("amounts", amounts);
        model.addAttribute("id", id);
        return "dashboard/register_place";
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
        return "super/dash";
    }


}
