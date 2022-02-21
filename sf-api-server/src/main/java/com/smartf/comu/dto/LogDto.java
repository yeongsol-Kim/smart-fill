package com.smartf.comu.dto;

import com.smartf.comu.entity.Log;
import com.smartf.comu.entity.Pump;
import com.smartf.comu.entity.User;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogDto {


    //@NotNull
    private OffsetDateTime datetime;

    private Long pumpId;

    private Long amount;

    private Long branchId;

    private String carNumber;

    private String username;

    private String nickname;

    private String branchName;

    private String branchCeo;

    private String branchAddress;

    private String branchTEL;

    private String approvalNumber;

    private Long pumpNumber;

    private String product;

    public static LogDto from(Log log) {
        if(log == null) return null;


        return LogDto.builder()
                .branchName(log.getBranchName())
                .branchCeo(log.getBranchCeo())
                .branchAddress(log.getBranchAddress())
                .branchTEL(log.getBranchTEL())
                .username(log.getUsername())
                .nickname(log.getNickname())
                .approvalNumber(log.getApprovalNumber())
                .pumpNumber(log.getPumpNumber())
                .product(log.getProduct())
                .amount(log.getAmount())
                .datetime(log.getDateTime())
                .pumpId(log.getPumpId())
                .carNumber(log.getCarNumber())
                .build();
    }
}
