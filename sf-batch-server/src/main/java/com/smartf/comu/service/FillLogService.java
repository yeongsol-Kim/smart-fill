package com.smartf.comu.service;

import com.smartf.comu.domain.FillLog;
import com.smartf.comu.repository.FillLogRepository;

import java.util.List;

public class FillLogService {

    private final FillLogRepository fillLogRepository;

    public FillLogService(FillLogRepository fillLogRepository) {
        this.fillLogRepository = fillLogRepository;
    }

    public Long join(FillLog fillLog) {
        fillLogRepository.save(fillLog);
        return fillLog.getId();
    }

    public List<FillLog> findFillLogs() {
        return fillLogRepository.findAll();
    }
}
