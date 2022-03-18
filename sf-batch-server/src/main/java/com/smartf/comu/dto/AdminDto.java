package com.smartf.comu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {

    @Size(min = 2, max = 20)
    private String username;

    @Size(min = 8, max = 16)
    private String password;

    @Size(min = 8, max = 16)
    private String passwordConfirm;

    @Size(max = 10)
    private Long dependentId;
}
