package com.smartf.comu.service;

import com.smartf.comu.domain.TankFill;
import com.smartf.comu.dto.TankFillDto;
import com.smartf.comu.repository.TankFillRepository;
import com.smartf.comu.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TankFillService {

    private final TankFillRepository tankFillRepository;

    public Long saveTankFill(TankFillDto dto) {

        TankFill tankFill = TankFill.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .amount(dto.getAmount())
                .companyId(SecurityUtil.getCurrentDependentId().orElse(null))
                .build();

        TankFill save = tankFillRepository.save(tankFill);

        return save.getId();
    }

}
