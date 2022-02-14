package com.smartf.comu.repository;

import com.smartf.comu.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaCarRepository extends JpaRepository<Car, Long> {

    @Override
    Optional<Car> findById(Long aLong);


    Car findByCarNumber(Long aLong);

}
