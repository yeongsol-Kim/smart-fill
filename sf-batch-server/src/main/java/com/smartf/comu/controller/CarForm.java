package com.smartf.comu.controller;

public class CarForm {
    private Long carNumber;
    private String car_type;
    private Long years;
    private Long registration_number;

    public Long getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(Long carNumber) {
        this.carNumber = carNumber;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public Long getYears() {
        return years;
    }

    public void setYears(Long years) {
        this.years = years;
    }

    public Long getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(Long registration_number) {
        this.registration_number = registration_number;
    }
}
