package com.smartf.comu.service;

import com.smartf.comu.domain.Log;
import com.smartf.comu.dto.LogReportDto;
import com.smartf.comu.repository.FillLogRepository;
import com.smartf.comu.util.SecurityUtil;
import org.springframework.data.domain.Sort;
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

    public List<Log> getLogsByBranchId(Long branchId) {
        return fillLogRepository.findByBranchId(branchId);

        // Sort
        //return fillLogRepository.findByBranchId(branchId, Sort.by(Sort.Direction.DESC, "datetime"));
    }

    public List<Log> getMyBranchLogs() {
        Long id = SecurityUtil.getCurrentDependentId().orElse(null);
        return fillLogRepository.findByBranchId(id);
        //return fillLogRepository.findByBranchId(id, Sort.by(Sort.Direction.DESC, "datetime"));
    }

    public List<LogReportDto> getMyGraphData() {

        List<LogReportDto> logReportDto = fillLogRepository.findByBranchIdGroupByMonthWithJPQL(SecurityUtil.getCurrentDependentId().orElse(null));
        return logReportDto;
    }

    public List<LogReportDto> getMyGraphDataByCarNumber(String carNumber) {
        List<LogReportDto> logReportDto = fillLogRepository.findByCarNumberGroupByMonthWithJPQL(carNumber);
        return logReportDto;
    }
}
