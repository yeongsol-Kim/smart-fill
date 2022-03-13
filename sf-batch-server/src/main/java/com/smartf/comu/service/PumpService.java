package com.smartf.comu.service;

import com.smartf.comu.domain.Pump;
import com.smartf.comu.dto.PumpDto;
import com.smartf.comu.repository.PumpRepository;
import com.smartf.comu.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PumpService {

    private final PumpRepository pumpRepository;

    public PumpService(PumpRepository pumpRepository) {
        this.pumpRepository = pumpRepository;
    }


    public List<Pump> getMyPumps() {
        return pumpRepository.findByBranchId(SecurityUtil.getCurrentDependentId().orElse(null));
    }

    public void addPump(PumpDto pumpDto) {
        Pump pump = Pump.builder()
                .id(pumpDto.getId())
                .number(pumpDto.getNumber())
                .reservoirId(pumpDto.getReservoirId())
                .branchId(SecurityUtil.getCurrentDependentId().orElse(null))
                .state(1L)
                .build();

        pumpRepository.save(pump);
    }
}
