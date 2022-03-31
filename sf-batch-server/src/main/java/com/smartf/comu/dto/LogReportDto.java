package com.smartf.comu.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogReportDto {

    private int month;

    private long sumAmount;
}
