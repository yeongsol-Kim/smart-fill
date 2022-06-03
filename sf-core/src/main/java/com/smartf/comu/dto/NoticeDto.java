package com.smartf.comu.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDto {

    @NotEmpty
    @Size(max = 40)
    private String title;

    @NotEmpty
    @Size(max = 410)
    private String contents;
}
