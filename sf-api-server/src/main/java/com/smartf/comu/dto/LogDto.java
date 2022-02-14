package com.smartf.comu.dto;

import com.smartf.comu.entity.Log;
import com.smartf.comu.entity.Pump;
import com.smartf.comu.entity.User;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogDto {

    @NotNull
    @Size(min = 3, max = 50)
    private Long liter;

    @NotNull
    @Size(min = 3, max = 50)
    private String datetime;


    private Long pump;

    public static LogDto from(Log log) {
        if(log == null) return null;


        return LogDto.builder()
                .liter(log.getLiter())
                .datetime(log.getDatetime())
                .pump(log.getPump().getNumber())
                .build();
    }
}
