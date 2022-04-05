package com.smartf.comu.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@Getter
@Setter
public class Car {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "years")
    private Long years;

    @Column(name = "car_type")
    private String carType;

    @Column(name = "registration_number")
    private Long registrationNumber;
}
