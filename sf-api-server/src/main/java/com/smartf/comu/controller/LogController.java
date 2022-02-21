package com.smartf.comu.controller;

import com.smartf.comu.dto.LogDto;
import com.smartf.comu.entity.Log;
import com.smartf.comu.service.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;

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

    @GetMapping("/fill-logs/users")
    public Collection<Log> getMyLogs() {
        Collection<Log> log = logService.getMyLogs();

        return log;
    }

    @GetMapping("/fill-logs/users/{username}")
    public Collection<Log> findByMembers(@PathVariable String username) {
        Collection<Log> log = logService.getLogs(username);

        return log;
    }
}
