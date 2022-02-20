package com.smartf.comu.controller;

import com.smartf.comu.dto.LogDto;
import com.smartf.comu.entity.Log;
import com.smartf.comu.service.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LogController {
    private LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/fill-logs")
    public ResponseEntity<LogDto> addLog(@Valid @RequestBody LogDto logDto) {
        return ResponseEntity.ok(logService.addLog(logDto));
    }

    @GetMapping("/fill-logs/members/{memberId}")
    public ResponseEntity<LogDto> findByMembers(@PathVariable Long memberId) {
        LogDto log = logService.findByMemberId(memberId);

        return ResponseEntity.ok(log);
        //return ResponseEntity.ok(log);
    }
}
