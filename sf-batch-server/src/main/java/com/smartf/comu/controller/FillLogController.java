package com.smartf.comu.controller;

import com.smartf.comu.domain.FillLog;
import com.smartf.comu.service.FillLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FillLogController {
    private final FillLogService fillLogService;

    @Autowired
    public FillLogController(FillLogService fillLogService) {
        this.fillLogService = fillLogService;
    }

    @GetMapping("/fillLogs")
    public String fillLogList(Model model) {
        List<FillLog> fillLogs = fillLogService.findFillLogs();
        model.addAttribute("fillLogs", fillLogs);
        return "fillLogs/fillLogList";
    }

    @GetMapping("/fillLogs/test")
    public String memberSampleData() {
        FillLog fillLog1 = new FillLog(1L,1L ,13L ,1L);
        fillLogService.join(fillLog1);

        FillLog fillLog2 = new FillLog(2L,1L ,26L ,1L);
        fillLogService.join(fillLog2);

        FillLog fillLog3 = new FillLog(3L,1L ,34L ,1L);
        fillLogService.join(fillLog3);

        return "redirect:/";
    }

}
