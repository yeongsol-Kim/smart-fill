package com.smartf.comu.repository;

import com.smartf.comu.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUsername(String username);
    List<Admin> findByDependentId(Long id);

}
