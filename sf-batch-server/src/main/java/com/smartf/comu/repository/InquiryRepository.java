package com.smartf.comu.repository;

import com.smartf.comu.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findByCompanyId(Long companyId);
}
