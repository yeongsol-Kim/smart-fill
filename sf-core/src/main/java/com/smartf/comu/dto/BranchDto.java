package com.smartf.comu.dto;

import lombok.*;


@Data
@Builder
public class BranchDto {

    private Long id;

    private String name;

    private Long companyId;

    private String address;

}

