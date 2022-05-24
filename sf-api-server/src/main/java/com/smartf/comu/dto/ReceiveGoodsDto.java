package com.smartf.comu.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveGoodsDto {

    private String username;

    private String password;

    private Long tankId;

}
