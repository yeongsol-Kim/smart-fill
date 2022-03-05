package com.smartf.comu.repository;

import com.smartf.comu.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FillLogRepository extends JpaRepository<Log, Long> {

    List<Log> findByBranchId(Long branchId);

}
