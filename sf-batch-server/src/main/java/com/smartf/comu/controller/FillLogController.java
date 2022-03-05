package com.smartf.comu.controller;

import com.smartf.comu.domain.Log;
import com.smartf.comu.domain.Reservoir;
import com.smartf.comu.dto.ReservoirDto;
import com.smartf.comu.service.FillLogService;
import com.smartf.comu.service.ReservoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FillLogController {
    private final FillLogService fillLogService;
    private final ReservoirService reservoirService;

    @Autowired
    public FillLogController(FillLogService fillLogService, ReservoirService reservoirService) {
        this.fillLogService = fillLogService;
        this.reservoirService = reservoirService;
    }


    @GetMapping("/")
    public String dashboard(Model model) {
        List<Log> fillLogs = fillLogService.getMyBranchLogs();
        List<Reservoir> reservoirs = reservoirService.getMyReservoir();
        model.addAttribute("fillLogs", fillLogs);
        model.addAttribute("reservoirs", reservoirs);
        return "dashboard/register_place";
    }

    @PostMapping("/reservoir/put")
    public String reservoirPut(ReservoirDto reservoirDto) {
        reservoirService.putReservoir(reservoirDto);
        return "redirect:/";
    }

//    @GetMapping("/fillLogs")
//    public String fillLogList(Model model) {
//        List<FillLog> fillLogs = fillLogService.findFillLogs();
//        model.addAttribute("fillLogs", fillLogs);
//        return "fillLogs/fillLogList";
//    }
//
//    @GetMapping("/fillLogs/test")
//    public String memberSampleData() {
//        FillLog fillLog1 = new FillLog(1L,1L ,13L ,1L);
//        fillLogService.join(fillLog1);
//
//        FillLog fillLog2 = new FillLog(2L,1L ,26L ,1L);
//        fillLogService.join(fillLog2);
//
//        FillLog fillLog3 = new FillLog(3L,1L ,34L ,1L);
//        fillLogService.join(fillLog3);
//
//        return "redirect:/";
//    }

}
