package com.smartf.comu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InquiryRequestDto {

    @Size(max = 410)
    private String contents;

    @NotEmpty
    private Long inquiryId;
}
