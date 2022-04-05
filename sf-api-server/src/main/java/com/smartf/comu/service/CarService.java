package com.smartf.comu.service;


import com.smartf.comu.entity.Car;
import com.smartf.comu.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public Boolean isRegistrationCar(Long carNumber) {
        Car car = carRepository.findByCarNumber(carNumber);
        return car.getId() != null;
        //return UserDto.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }


    public List<Car> getCarListByCarNumber(String carNumber) {
        return carRepository.findByCarNumberContains(carNumber);

    }

    public List<Car> searchMyBranchCars(String carNumber, Long branchId) {
        return carRepository.findByCarNumberContainsAndBranchId(carNumber, branchId);

    }
}
