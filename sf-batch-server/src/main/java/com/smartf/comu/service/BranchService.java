package com.smartf.comu.service;

import com.smartf.comu.config.SecurityConfig;
import com.smartf.comu.domain.Branch;
import com.smartf.comu.repository.BranchRepository;
import com.smartf.comu.util.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public void addBranch(Branch branch) {
        branchRepository.save(branch);
    }

    public void addMyCompanyBranch(String name) {
        Branch branch = Branch.builder()
                .name(name)
                .companyId(SecurityUtil.getCurrentDependentId().orElse(null))
                .build();
        branchRepository.save(branch);
    }
}
