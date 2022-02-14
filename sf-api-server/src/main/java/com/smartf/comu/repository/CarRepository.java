package com.smartf.comu.repository;

import com.smartf.comu.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByCarNumber(Long carNumber);
}
