package com.smartf.comu.service;

import com.smartf.comu.entity.Log;
import com.smartf.comu.repository.LogRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public Log findByMemberId(Long memberId) {
        return logRepository.findByUserId(memberId);
    }
}
