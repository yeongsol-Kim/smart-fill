package com.smartf.comu.controller;

import com.smartf.comu.entity.Log;
import com.smartf.comu.service.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LogController {
    private LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/fill-logs/members/{memberId}")
    public ResponseEntity<Log> findByMembers(@PathVariable Long memberId) {
        Log log = logService.findByMemberId(memberId);

        log.setLiter(1L);
        return ResponseEntity.ok(log);
        //return ResponseEntity.ok(log);
    }
}
