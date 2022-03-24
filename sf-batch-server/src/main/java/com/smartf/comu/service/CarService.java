package com.smartf.comu.service;

import com.smartf.comu.domain.Car;
import com.smartf.comu.dto.CarDto;
import com.smartf.comu.repository.SpringDataJpaCarRepository;
import com.smartf.comu.util.SecurityUtil;

import java.util.List;
import java.util.Optional;

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

    public Car getEditCarInfo(Long id) throws Exception {
        Car car = isMyBranchCar(id);

        return car;
    }

    public void updateCar(CarDto carDto) throws Exception {
        Car car = isMyBranchCar(carDto.getId());

        car.setCarNumber(carDto.getCarNumber());
        car.setCarType(carDto.getCarType());
        car.setYears(carDto.getYears());
        car.setRegistrationNumber(carDto.getRegistrationNumber());

        carRepository.save(car);

    }

    private Car isMyBranchCar(Long id) throws Exception {
        Optional<Car> car = carRepository.findById(id);

        // 잘못된 uri (없는 직원)일 때 오류 발생
        car.orElseThrow(() -> new Exception("car is null"));

        // 다른 회사의 직원을 수정하려고 할 때 오류 발생
        if(car.get().getBranchId() != SecurityUtil.getCurrentDependentId().orElse(null)) {
            throw new Exception("not branch car");
        }

        return car.get();
    }

//    private void validateDuplicateMember(Member member) {
//        memberRepository.findByName(member.getUserName())
//                        .ifPresent(m -> {
//                            throw new IllegalStateException("이미 존재하는 회원입니다.");
//                        });
//    }
}
