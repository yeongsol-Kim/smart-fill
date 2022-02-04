package hello.hellospring.service;

import hello.hellospring.domain.Car;
import hello.hellospring.repository.SpringDataJpaCarRepository;

import java.util.List;

public class CarService {
    private final SpringDataJpaCarRepository carRepository;


    public CarService(SpringDataJpaCarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findCars() {
        return carRepository.findAll();
    }

    public Long insert(Car car) {
        carRepository.save(car);
        return car.getId();
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
