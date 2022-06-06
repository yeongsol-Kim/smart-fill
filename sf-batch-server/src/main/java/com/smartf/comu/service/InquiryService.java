package com.smartf.comu.service;

import com.smartf.comu.entity.Inquiry;
import com.smartf.comu.entity.InquiryRequest;
import com.smartf.comu.repository.InquiryRepository;
import com.smartf.comu.repository.InquiryRequestRepository;
import com.smartf.comu.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InquiryService {
    private final InquiryRepository inquiryRepository;
    private final InquiryRequestRepository inquiryRequestRepository;

    public InquiryService(InquiryRepository inquiryRepository, InquiryRequestRepository inquiryRequestRepository) {
        this.inquiryRepository = inquiryRepository;
        this.inquiryRequestRepository = inquiryRequestRepository;
    }

    public Inquiry insertMyInquiry(Inquiry inquiry) {
        inquiry.setCompanyId(SecurityUtil.getCurrentDependentId().orElse(null));
        return inquiryRepository.save(inquiry);
    }

    public InquiryRequest insertRequest(InquiryRequest inquiryRequest) {
        return inquiryRequestRepository.save(inquiryRequest);
    }

    public List<Inquiry> getMyCompanyInquiry() {
        Long companyId = SecurityUtil.getCurrentDependentId().orElse(0L);
        return inquiryRepository.findByCompanyId(companyId);
    }
}
