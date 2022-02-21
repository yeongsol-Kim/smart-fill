package com.smartf.comu.service;

import com.smartf.comu.dto.BranchDto;
import com.smartf.comu.dto.CompanyDto;
import com.smartf.comu.dto.LogDto;
import com.smartf.comu.dto.PumpDto;
import com.smartf.comu.entity.Log;
import com.smartf.comu.repository.BranchRepository;
import com.smartf.comu.repository.CompanyRepository;
import com.smartf.comu.repository.LogRepository;
import com.smartf.comu.repository.PumpRepository;
import com.smartf.comu.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Set;

@Service
public class LogService {
    private LogRepository logRepository;
    private PumpRepository pumpRepository;
    private BranchRepository branchRepository;
    private CompanyRepository companyRepository;

    public LogService(LogRepository logRepository, PumpRepository pumpRepository, BranchRepository branchRepository, CompanyRepository companyRepository) {
        this.logRepository = logRepository;
        this.pumpRepository = pumpRepository;
        this.branchRepository = branchRepository;
        this.companyRepository = companyRepository;
    }

    public LogDto addLog(LogDto logDto) {
        PumpDto pump = PumpDto.from(pumpRepository.findById(logDto.getPumpId()).orElse(null));
        BranchDto branch = BranchDto.from(branchRepository.findById(logDto.getBranchId()).orElse(null));
        System.out.println(branch.getCompanyId());
        CompanyDto company = CompanyDto.from(companyRepository.findById(branch.getCompanyId()).orElse(null));

        Log log = Log.builder()
                .pumpId(logDto.getPumpId())
                .carNumber(logDto.getCarNumber())
                .amount(logDto.getAmount())
                .username(logDto.getUsername())
                .nickname(logDto.getNickname())
                .pumpNumber(pump.getNumber())
                .branchName(branch.getName())
                .branchCeo(company.getCeo())
                .branchAddress(company.getAddress())
                .branchTEL(company.getTel())
                .dateTime(OffsetDateTime.now())
                .product("요소수")
                .build();

        return LogDto.from(logRepository.save(log));
    }

    public Set<Log> getLogs(String username) {
        return logRepository.findWithPumpsByUsername(username);
    }

    public Set<Log> getMyLogs() {
        return logRepository.findWithPumpsByUsername(SecurityUtil.getCurrentUsername().orElse(null));
    }

}
