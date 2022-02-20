package com.smartf.comu.dto;


import com.smartf.comu.entity.Log;
import com.smartf.comu.entity.Pump;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PumpDto {

    private Long number;

    private Long state;

    private Long stationId;
    public static PumpDto from(Pump pump) {
        if(pump == null) return null;


        return PumpDto.builder()
                .number(pump.getNumber())
                .stationId(pump.getStationId())
                .build();
    }
}
