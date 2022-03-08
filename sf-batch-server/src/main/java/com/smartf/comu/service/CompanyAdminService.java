package com.smartf.comu.service;

import com.smartf.comu.domain.Admin;
import com.smartf.comu.domain.Branch;
import com.smartf.comu.repository.AdminRepository;
import com.smartf.comu.repository.BranchRepository;
import com.smartf.comu.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyAdminService {

    private final AdminRepository adminRepository;
    private final BranchRepository branchRepository;

    public CompanyAdminService(AdminRepository adminRepository, BranchRepository branchRepository) {
        this.adminRepository = adminRepository;
        this.branchRepository = branchRepository;
    }

    public List<Branch> getMyBranches() {
        return branchRepository.findByCompanyId(SecurityUtil.getCurrentDependentId().orElse(null));
    }

    public List<Admin> getMyAdmins() {
        return adminRepository.findByDependentId(SecurityUtil.getCurrentDependentId().orElse(null));
    }
}
