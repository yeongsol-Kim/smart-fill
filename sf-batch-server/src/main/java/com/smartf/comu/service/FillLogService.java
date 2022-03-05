package com.smartf.comu.service;

import com.smartf.comu.domain.Log;
import com.smartf.comu.repository.FillLogRepository;
import com.smartf.comu.util.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FillLogService {

    private final FillLogRepository fillLogRepository;

    public FillLogService(FillLogRepository fillLogRepository) {
        this.fillLogRepository = fillLogRepository;
    }

    public Long join(Log fillLog) {
        fillLogRepository.save(fillLog);
        return fillLog.getId();
    }

    public List<Log> findFillLogs() {
        return fillLogRepository.findAll();
    }

    public List<Log> getMyBranchLogs() {
        Long id = SecurityUtil.getCurrentBranchId().orElse(null);
        return fillLogRepository.findByBranchId(id);
    }
}
