package com.smartf.comu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {


    @GetMapping("/das")
    public String dashboard(Model model) {
        return "dashboard/register_place";
    }

    @GetMapping("/super")
    public String superDash(Model model) {
        return "super/register_place";
    }

}
