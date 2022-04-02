package com.smartf.comu.repository;

import com.smartf.comu.domain.Log;
import com.smartf.comu.dto.LogReportDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FillLogRepository extends JpaRepository<Log, Long> {

    List<Log> findByBranchId(Long branchId);

    @Query(value =
            "SELECT " +
            "new com.smartf.comu.dto.LogReportDto(MONTH(l.datetime) as month, SUM(l.amount) as sumAmount) " +
            "FROM Log l " +
            "GROUP BY MONTH(l.datetime)"
    )
    List<LogReportDto> findGroupByMonthWithJPQL();

    @Query(value =
            "SELECT " +
                    "new com.smartf.comu.dto.LogReportDto(MONTH(l.datetime) as month, SUM(l.amount) as sumAmount) " +
                    "FROM Log l " +
                    "WHERE l.branchId = :id " +
                    "GROUP BY MONTH(l.datetime)"
    )
    List<LogReportDto> findByBranchIdGroupByMonthWithJPQL(Long id);

    @Query(value =
            "SELECT " +
                    "new com.smartf.comu.dto.LogReportDto(MONTH(l.datetime) as month, SUM(l.amount) as sumAmount) " +
                    "FROM Log l " +
                    "WHERE l.carNumber = :id " +
                    "GROUP BY MONTH(l.datetime)"
    )
    List<LogReportDto> findByCarNumberGroupByMonthWithJPQL(String id);

}
