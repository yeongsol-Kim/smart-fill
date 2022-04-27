package com.smartf.comu.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoDto {

    private Long id;

    private Long branchId;

    @NotBlank
    @Size(max = 32)
    private String username;

    @NotBlank
    @Size(max = 32)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(max = 20)
    private String password;

    @NotBlank
    @Size(max = 50)
    private String address;

    @NotBlank
    @Size(min = 8, max = 15)
    private String phoneNumber;

    private MultipartFile file;

    private Long activated;
}
