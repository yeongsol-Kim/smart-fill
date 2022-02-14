package com.smartf.comu.controller;

import com.smartf.comu.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/cars/number/{number}")
    public ResponseEntity<Boolean> isCar(@PathVariable Long number) {
        boolean isCar = carService.isRegistrationCar(number);
        if (isCar) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }

    }

}
