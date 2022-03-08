package com.smartf.comu.service;

import com.smartf.comu.domain.Reservoir;
import com.smartf.comu.dto.ReservoirDto;
import com.smartf.comu.repository.ReservoirRepository;
import com.smartf.comu.util.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservoirService {

    private final ReservoirRepository reservoirRepository;

    public ReservoirService(ReservoirRepository repository) {
        this.reservoirRepository = repository;
    }


    public List<Reservoir> getMyReservoir() {
        Long id = SecurityUtil.getCurrentDependentId().orElse(null);
        return reservoirRepository.findByBranchId(id);
    }

    public Long putReservoir(ReservoirDto reservoirDto) {
        Optional<Reservoir> reservoir = reservoirRepository.findById(reservoirDto.getId());
        Double fuelLevel = reservoir.orElse(null).getFuelLevel();
        reservoir.orElse(null).setFuelLevel(fuelLevel + reservoirDto.getAmount());
        reservoirRepository.save(reservoir.orElse(null));
        return 1L;

    }
}
