package com.smartf.comu.repository;

import com.smartf.comu.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByCarNumber(Long carNumber);

    List<Car> findByCarNumberContains(String carNumber);

    List<Car> findByCarNumberContainsAndBranchId(String carNumber, Long branchId);
}
