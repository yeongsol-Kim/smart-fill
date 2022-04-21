package com.smartf.comu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservoirDto {

    private Long id;

    private Double amount;

    private String reservoirName;

    private Long branchId;

    private Double fuelMax;
}
