package com.smartf.comu.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private Long id;

    private String carNumber;

    private String carType;

    private Long years;

    private Long registrationNumber;
}
