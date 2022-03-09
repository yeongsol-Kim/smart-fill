package com.smartf.comu.service;

import com.smartf.comu.domain.Admin;
import com.smartf.comu.domain.AdminAuthority;
import com.smartf.comu.domain.Member;
import com.smartf.comu.dto.AdminDto;
import com.smartf.comu.repository.AdminAuthorityRepository;
import com.smartf.comu.repository.AdminRepository;
import com.smartf.comu.util.SecurityUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final AdminAuthorityRepository adminAuthorityRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, AdminAuthorityRepository adminAuthorityRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.adminAuthorityRepository = adminAuthorityRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Long addAdmin(AdminDto adminDto) {


        adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));

        System.out.println(adminDto.getDependentId());
        Admin admin = Admin.builder()
                .username(adminDto.getUsername())
                .password(adminDto.getPassword())
                .dependentId(adminDto.getDependentId())
                .build();

        Long id = adminRepository.save(admin).getId();

        AdminAuthority adminAuthority = AdminAuthority.builder()
                .adminId(id)
                .authorityName("ROLE_ADMIN")
                .build();

        adminAuthorityRepository.save(adminAuthority);


        return id;
    }

    public Long addBranchAdmin(AdminDto adminDto) {


        adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));

        Admin admin = Admin.builder()
                .username(adminDto.getUsername())
                .password(adminDto.getPassword())
                .dependentId(adminDto.getDependentId())
                .build();

        Long id = adminRepository.save(admin).getId();

        AdminAuthority adminAuthority = AdminAuthority.builder()
                .adminId(id)
                .authorityName("ROLE_BRANCH")
                .build();

        adminAuthorityRepository.save(adminAuthority);


        return id;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);

        return admin;
    }
}
