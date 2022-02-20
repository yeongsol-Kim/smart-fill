package com.smartf.comu.dto;

import com.smartf.comu.entity.Company;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private String name;

    private String ceo;

    private String tel;

    private String address;

    public static CompanyDto from(Company company) {
        if(company == null) return null;

        return CompanyDto.builder()
                .name(company.getName())
                .ceo(company.getCeo())
                .tel(company.getTel())
                .address(company.getAddress())
                .build();
    }
}
