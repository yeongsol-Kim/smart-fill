package com.smartf.comu.dto;

import com.smartf.comu.entity.Branch;
import com.smartf.comu.entity.Log;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {

    private Long id;

    private String name;

    private Long companyId;

    public static BranchDto from(Branch branch) {
        if(branch == null) return null;


        return BranchDto.builder()
                .id(branch.getId())
                .name(branch.getName())
                .companyId(branch.getCompanyId())
                .build();
    }
}

