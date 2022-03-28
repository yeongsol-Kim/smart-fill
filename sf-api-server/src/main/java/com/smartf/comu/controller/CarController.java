package com.smartf.comu.controller;

import com.smartf.comu.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
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
