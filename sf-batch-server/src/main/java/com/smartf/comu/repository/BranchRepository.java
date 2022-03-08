package com.smartf.comu.repository;

import com.smartf.comu.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    List<Branch> findByCompanyId(Long id);
}
