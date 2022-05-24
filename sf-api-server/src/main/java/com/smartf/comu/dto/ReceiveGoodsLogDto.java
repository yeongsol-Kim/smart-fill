package com.smartf.comu.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveGoodsLogDto {

    private Long companyId;

    private Long tankId;

    private Long amount;

    private LocalDateTime ReceiveGoodsDatetime;
}
