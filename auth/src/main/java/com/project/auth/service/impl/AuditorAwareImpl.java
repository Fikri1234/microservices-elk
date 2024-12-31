package com.project.auth.service.impl;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by user on Jul, 2024
 */

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        String user;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String string) {
            user = string;
        } else if (principal instanceof UserDetails userDetails) {
            user = userDetails.getUsername();
        } else {
            user = String.valueOf(principal);
        }
        return Optional.ofNullable(user);
    }
}
