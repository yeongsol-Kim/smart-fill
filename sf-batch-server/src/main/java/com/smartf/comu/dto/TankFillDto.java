package com.smartf.comu.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TankFillDto {

    private Long companyId;

    private String username;

    private String password;

    private Integer amount;

}
