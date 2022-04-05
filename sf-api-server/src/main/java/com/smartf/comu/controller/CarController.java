package com.smartf.comu.controller;

import com.smartf.comu.entity.Car;
import com.smartf.comu.service.CarService;
import com.smartf.comu.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CarController {
    private final CarService carService;
    private final UserService userService;

    public CarController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }


    @GetMapping("/cars/carNumbers/{number}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Car> searchCarList(@PathVariable String number) {
        return carService.getCarListByCarNumber(number);

    }

    @GetMapping("/cars/branchId/carNumbers/{number}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Car> searchMyBranchCarList(@PathVariable String number, Authentication authentication) {
        Long branchId = userService.getMyUserWithAuthorities().getBranchId();

        return carService.searchMyBranchCars(number, branchId);

    }

    @GetMapping("/cars/numbers/{number}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Boolean> isCar(@PathVariable Long number) {
        System.out.println(number);
        boolean isCar = carService.isRegistrationCar(number);
        if (isCar) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }

    }

}
