package com.smartf.comu.util;

import com.smartf.comu.domain.Admin;
import com.smartf.comu.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    private SecurityUtil() {
    }

    public static Optional<Long> getCurrentId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        Long id = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            Admin springSecurityUser = (Admin) authentication.getPrincipal();
            id = springSecurityUser.getId();
        } else if (authentication.getPrincipal() instanceof Long) {
            id = (Long) authentication.getPrincipal();
        }

        return Optional.ofNullable(id);
    }

    public static Optional<Long> getCurrentDependentId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        Long id = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            Admin springSecurityUser = (Admin) authentication.getPrincipal();
            id = springSecurityUser.getDependentId();
        } else if (authentication.getPrincipal() instanceof Long) {
            id = (Long) authentication.getPrincipal();
        }

        return Optional.ofNullable(id);
    }
}
