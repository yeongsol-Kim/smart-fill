package com.smartf.comu.service;

import com.smartf.comu.domain.Admin;
import com.smartf.comu.domain.AdminAuthority;
import com.smartf.comu.dto.AdminDto;
import com.smartf.comu.repository.AdminAuthorityRepository;
import com.smartf.comu.repository.AdminRepository;
import com.smartf.comu.util.SecurityUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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




    // 회사 관리자 생성 (추후 함수 분리)
    public Long addAdmin(AdminDto adminDto) {

        adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));

        Admin admin = Admin.builder()
                .username(adminDto.getUsername())
                .password(adminDto.getPassword())
                .dependentId(adminDto.getDependentId())
                .build();

        validateDuplicateAdmin(admin);

        Long id = adminRepository.save(admin).getId();

        AdminAuthority adminAuthority = AdminAuthority.builder()
                .adminId(id)
                .authorityName("ROLE_ADMIN")
                .build();

        adminAuthorityRepository.save(adminAuthority);


        return id;
    }

    // 지점 관리자 생성 (추후 함수 분리)
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

    private void validateDuplicateAdmin(Admin admin) {
        adminRepository.findByUsername(admin.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepository.findByUsername(username);

        return admin.orElse(null);
    }
}
