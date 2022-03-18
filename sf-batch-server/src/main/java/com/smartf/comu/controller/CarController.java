package com.smartf.comu.controller;

import com.smartf.comu.domain.Car;
import com.smartf.comu.dto.CarDto;
import com.smartf.comu.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/cars")
    @PreAuthorize("hasRole('BRANCH')")
    public String carList(Model model) {
        List<Car> cars = carService.getMyBranchCarList();
        model.addAttribute("cars", cars);
        return "cars/carList";
    }

    @DeleteMapping("/cars/{id}")
    @PreAuthorize("hasRole('BRANCH')")
    public String carDelete(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }

    @GetMapping("/cars/new")
    @PreAuthorize("hasRole('BRANCH')")
    public String createForm(Model model) {
        return "cars/createCarForm";
    }

    @PostMapping("/cars/new")
    @PreAuthorize("hasRole('BRANCH')")
    public String create(CarDto carDto) {
        carService.addCar(carDto);

        return "redirect:/cars";
    }



    // --------------------------------- 앱 통신

    @ResponseBody
    @GetMapping("/cars/number/{number}")
    public Boolean isCar(@PathVariable Long number) {
        boolean isCar = carService.isRegistrationCar(number);
        return isCar;
    }
}
