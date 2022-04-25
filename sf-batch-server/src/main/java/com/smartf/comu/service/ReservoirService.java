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

    // 지점의 저장조 조회
    public List<Reservoir> getReservoirs(Long branchId) {
        return reservoirRepository.findByBranchId(branchId);
    }

    // 저장조 조회 (지점 관리자)
    public List<Reservoir> getMyReservoirs() {
        Long id = SecurityUtil.getCurrentDependentId().orElse(null);
        return reservoirRepository.findByBranchId(id);
    }

    public void addReservoir(ReservoirDto reservoirDto) {
        Reservoir reservoir = Reservoir.builder()
                //.branchId(SecurityUtil.getCurrentDependentId().orElse(null))
                .branchId(reservoirDto.getBranchId())
                .reservoirName(reservoirDto.getReservoirName())
                .fuelMax(reservoirDto.getFuelMax())
                .fuelLevel(0D)
                .build();

        reservoirRepository.save(reservoir);
    }

    public Long putReservoir(ReservoirDto reservoirDto) {
        Optional<Reservoir> reservoir = reservoirRepository.findById(reservoirDto.getId());
        Double fuelLevel = reservoir.orElse(null).getFuelLevel();
        reservoir.orElse(null).setFuelLevel(fuelLevel + reservoirDto.getAmount());
        reservoirRepository.save(reservoir.orElse(null));
        return 1L;

    }

    public void deleteReservoir(Long id) {
        reservoirRepository.deleteById(id);
    }

    public List<Reservoir> getMyBranchReservoirs(Long id) {

        // 접근권한 검사!!!!!!!! 추가

        return reservoirRepository.findByBranchId(id);
    }
}
