package com.smartf.comu.repository;

import com.smartf.comu.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
   Log findByUserId(Long userId);
}
