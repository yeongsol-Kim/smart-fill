package com.smartf.comu.service;

import com.smartf.comu.domain.Car;
import com.smartf.comu.dto.CarDto;
import com.smartf.comu.repository.SpringDataJpaCarRepository;
import com.smartf.comu.util.SecurityUtil;

import java.util.List;

public class CarService {
    private final SpringDataJpaCarRepository carRepository;


    public CarService(SpringDataJpaCarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findCars() {
        return carRepository.findAll();
    }

    public List<Car> getMyBranchCarList() {
        Long branchId = SecurityUtil.getCurrentDependentId().orElse(null);
        return carRepository.findByBranchId(branchId);
    }


    public void addCar(CarDto carDto) {
        Car car = Car.builder()
                .carNumber(carDto.getCarNumber())
                .carType(carDto.getCarType())
                .years(carDto.getYears())
                .registrationNumber(carDto.getRegistrationNumber())
                .branchId(SecurityUtil.getCurrentDependentId().orElse(null))
                .build();

        carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Boolean isRegistrationCar(Long number) {
        if(carRepository.findByCarNumber(number) == null) {
            return false;
        } else {
            return true;
        }
    }


//    private void validateDuplicateMember(Member member) {
//        memberRepository.findByName(member.getUserName())
//                        .ifPresent(m -> {
//                            throw new IllegalStateException("이미 존재하는 회원입니다.");
//                        });
//    }
}
