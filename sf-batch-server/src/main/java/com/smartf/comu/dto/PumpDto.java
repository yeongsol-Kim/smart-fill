package com.smartf.comu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PumpDto {

    private String id;

    private Long reservoirId;

    private Long number;

    private Long branchId;
}
