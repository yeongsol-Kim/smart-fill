package com.smartf.comu.dto.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AdminRegisterForm {

    @NotEmpty
    @Size(min = 4, max = 20)
    private String username;

    @NotEmpty
    @Size(min = 8, max = 16)
    private String password;

    @NotEmpty
    @Size(min = 8, max = 16)
    private String passwordConfirm;

    @NotNull
    private Long dependentId;
}
