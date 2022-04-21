package com.smartf.comu.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoDto {
    private Long id;
    private Long branchId;
    private String username;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private MultipartFile file;
    private Long activated;
}
