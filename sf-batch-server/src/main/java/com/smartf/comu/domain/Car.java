package com.smartf.comu.domain;

import lombok.*;

import javax.persistence.*;

@Table(name = "cars")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="car_number")
    private Long carNumber;

    @Column(name="years")
    private Long years;

    @Column(name="car_type")
    private String carType;

    @Column(name="registration_number")
    private Long registrationNumber;

    @Column(name = "branch_id")
    private Long branchId;


}
