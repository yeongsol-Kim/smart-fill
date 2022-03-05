package com.smartf.comu.repository;

import com.smartf.comu.domain.Reservoir;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservoirRepository extends JpaRepository<Reservoir, Long> {
    List<Reservoir> findByBranchId(Long branchId);
}
