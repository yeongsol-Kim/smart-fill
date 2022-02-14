package com.smartf.comu.repository;

import com.smartf.comu.domain.FillLog;

import java.util.List;

public interface FillLogRepository {
    FillLog save(FillLog fillLog);
    List<FillLog> findAll();
}
