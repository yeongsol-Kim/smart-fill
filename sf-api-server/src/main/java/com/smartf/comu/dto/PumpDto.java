package com.smartf.comu.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PumpDto {

    private Long number;

    private Long state;
}
