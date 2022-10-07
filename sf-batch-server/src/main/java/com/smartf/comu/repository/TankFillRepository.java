package com.smartf.comu.repository;

import com.smartf.comu.domain.TankFill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TankFillRepository extends JpaRepository<TankFill, Long> {
}
