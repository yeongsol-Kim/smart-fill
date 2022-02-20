package com.smartf.comu.repository;

import com.smartf.comu.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LogRepository extends JpaRepository<Log, Long> {
   Optional<Log> findById(Long id);
   Optional<Log> findWithPumpsByUserId(Long userId);
}
