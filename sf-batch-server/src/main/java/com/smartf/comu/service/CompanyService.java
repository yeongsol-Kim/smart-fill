package com.smartf.comu.service;

import com.smartf.comu.domain.Branch;
import com.smartf.comu.entity.Inquiry;
import com.smartf.comu.entity.Company;
import com.smartf.comu.repository.BranchRepository;
import com.smartf.comu.repository.CompanyRepository;
import com.smartf.comu.repository.InquiryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final BranchRepository branchRepository;
    private final InquiryRepository inquiryRepository;

    public CompanyService(CompanyRepository companyRepository, BranchRepository branchRepository, InquiryRepository inquiryRepository) {
        this.companyRepository = companyRepository;
        this.branchRepository = branchRepository;
        this.inquiryRepository = inquiryRepository;
    }

    public List<Company> findAllCompany() {
        return companyRepository.findAll();
    }

    // 거래처 목록에 사용할 정보
//    public List<Company> findAllCompanyListInfo() {
//        return companyRepository.findAlls();
//    }


    public Optional<Company> getCompanyInfo(Long id) {
        return companyRepository.findById(id);
    }

    public List<Branch> getBranches(Long id) {
        return branchRepository.findByCompanyId(id);
    }

    // 삽입
    public Company insertCompany(Company company) {
        return companyRepository.save(company);
    }


    // 상태 설정
    public void setStatus(Long id, int status) {
        Company company = companyRepository.findById(id).orElse(null);
        company.setStatus(status);
        companyRepository.save(company);
    }

    public List<Inquiry> getInquiries(Long id) {
        return inquiryRepository.findByCompanyId(id);
    }
}
