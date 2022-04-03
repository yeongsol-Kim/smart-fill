package com.smartf.comu.service;

import com.smartf.comu.config.SecurityConfig;
import com.smartf.comu.domain.Admin;
import com.smartf.comu.domain.Branch;
import com.smartf.comu.repository.BranchRepository;
import com.smartf.comu.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    // 접근 권한 검사 (본인 회사의 지점인지 검사)
    public Boolean isMyBranch(Long branchId) throws Exception {
        Optional<Branch> branch = branchRepository.findById(branchId);

        if(branch.get().getCompanyId() == SecurityUtil.getCurrentDependentId().orElse(null)) {
            return true;
        } else {
            return false;
        }

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
