package com.smartf.comu.repository;

import com.smartf.comu.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUsername(String username);
    List<Admin> findByDependentId(Long id);

}
