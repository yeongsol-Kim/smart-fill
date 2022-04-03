package com.smartf.comu.controller;

import com.smartf.comu.service.AdminService;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
}
