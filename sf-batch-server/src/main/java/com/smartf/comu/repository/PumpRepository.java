package com.smartf.comu.repository;

import com.smartf.comu.domain.Pump;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PumpRepository extends JpaRepository<Pump, Long> {

    List<Pump> findByBranchId(Long id);
}
