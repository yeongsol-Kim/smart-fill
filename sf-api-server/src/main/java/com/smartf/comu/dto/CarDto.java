package com.smartf.comu.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String carNumber;

    @NotNull
    @Size(min = 3, max = 50)
    private Long years;

    @NotNull
    @Size(min = 3, max = 50)
    private String carType;

    @NotNull
    @Size(min = 3, max = 50)
    private Long registrationNumber;

//    @NotNull
//    @Size(min = 3, max = 50)
//    private String branchId;
}
