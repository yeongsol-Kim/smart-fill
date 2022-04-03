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


    // 지점의 주입 내역 조회
    public List<Log> getBranchLogs(Long branchId) {
        return fillLogRepository.findByBranchId(branchId);
    }

    // 주입 내역 조회 (지점 관리자)
    public List<Log> getMyBranchLogs() {
        Long id = SecurityUtil.getCurrentDependentId().orElse(null);
        return fillLogRepository.findByBranchId(id);
        //return fillLogRepository.findByBranchId(id, Sort.by(Sort.Direction.DESC, "datetime"));
    }

    // 지점의 월 주입 통계
    public List<LogReportDto> getGraphData(Long branchId) {

        List<LogReportDto> logReportDto = fillLogRepository.findByBranchIdGroupByMonthWithJPQL(branchId);
        return logReportDto;
    }

    // 월 주입 통계 (지점 관리자)
    public List<LogReportDto> getMyGraphData() {
        Long id = SecurityUtil.getCurrentDependentId().orElse(null);
        List<LogReportDto> logReportDto = fillLogRepository.findByBranchIdGroupByMonthWithJPQL(id);
        return logReportDto;
    }

    public List<LogReportDto> getMyGraphDataByCarNumber(String carNumber) {
        List<LogReportDto> logReportDto = fillLogRepository.findByCarNumberGroupByMonthWithJPQL(carNumber);
        return logReportDto;
    }
}
