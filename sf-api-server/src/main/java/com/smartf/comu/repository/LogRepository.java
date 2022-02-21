package com.smartf.comu.repository;

import com.smartf.comu.dto.LogDto;
import com.smartf.comu.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface LogRepository extends JpaRepository<Log, Long> {
   Optional<Log> findById(Long id);
   Set<Log> findWithPumpsByUsername(String username);
}
