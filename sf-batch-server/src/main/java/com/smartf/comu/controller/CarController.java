package com.smartf.comu.controller;

import com.smartf.comu.domain.Car;
import com.smartf.comu.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String carList(Model model) {
        List<Car> cars = carService.findCars();
        model.addAttribute("cars", cars);
        return "cars/carList";
    }

    @DeleteMapping("/cars/{id}")
    public String carDelete(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }

    @GetMapping("/cars/new")
    public String createForm(Model model) {
        return "cars/createCarForm";
    }

    @PostMapping("/cars/new")
    public String create(CarForm form) {
        Car car = new Car();
        car.setCarNumber(form.getCarNumber());
        car.setCar_type(form.getCar_type());
        car.setYears(form.getYears());
        car.setRegistration_number(form.getRegistration_number());

        carService.insert(car);

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
