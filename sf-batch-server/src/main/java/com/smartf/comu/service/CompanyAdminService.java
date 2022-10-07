package com.smartf.comu.service;

import com.smartf.comu.domain.Admin;
import com.smartf.comu.domain.Branch;
import com.smartf.comu.dto.BranchDto;
import com.smartf.comu.repository.AdminRepository;
import com.smartf.comu.repository.BranchRepository;
import com.smartf.comu.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyAdminService {

    private final AdminRepository adminRepository;
    private final BranchRepository branchRepository;

    public CompanyAdminService(AdminRepository adminRepository, BranchRepository branchRepository) {
        this.adminRepository = adminRepository;
        this.branchRepository = branchRepository;
    }

    // 로그인한 본사의 지사목록
    public List<BranchDto> getMyBranches() {
        List<Branch> branchList = branchRepository.findByCompanyId(SecurityUtil.getCurrentDependentId().orElse(null));

        List<BranchDto> branchDtoList = new ArrayList<>();
        branchList.stream().forEach(
                b -> branchDtoList.add(
                        BranchDto.builder()
                                .id(b.getId())
                                .companyId(b.getCompanyId())
                                .name(b.getName())
                                .address(b.getAddress())
                                .build()
                )
        );
        return branchDtoList;
    }


    public List<Admin> getMyAdmins() {
        return adminRepository.findByDependentId(SecurityUtil.getCurrentDependentId().orElse(null));
    }
}
