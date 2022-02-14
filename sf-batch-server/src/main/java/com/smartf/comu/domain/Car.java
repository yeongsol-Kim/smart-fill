package com.smartf.comu.domain;

import javax.persistence.*;

@Table(name = "cars")
@Entity
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="car_number")
    private Long carNumber;
    private Long years;
    private String car_type;
    private Long registration_number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(Long carNumber) {
        this.carNumber = carNumber;
    }

    public Long getYears() {
        return years;
    }

    public void setYears(Long years) {
        this.years = years;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public Long getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(Long registration_number) {
        this.registration_number = registration_number;
    }
}
