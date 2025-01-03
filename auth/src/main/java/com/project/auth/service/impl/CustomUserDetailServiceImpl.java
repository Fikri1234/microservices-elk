package com.project.auth.service.impl;

import com.project.auth.entity.RoleEntity;
import com.project.auth.entity.UserEntity;
import com.project.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by user on Jul, 2024
 */

@Service("customUserDetailService")
@AllArgsConstructor
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optional = userService.findFirstByUsernameOrEmail(username, username);
        if (optional.isPresent()) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            List<RoleEntity> roles = optional.get().getAuthorities().stream().toList();
            roles.stream().map(x -> authorities.add(new SimpleGrantedAuthority(x.getRoleCode())));

            return new User(optional.get().getUsername(), optional.get().getPassword(),
                    optional.get().getEmbeddedEntity().isActive(), optional.get().isAccountNonExpired(),
                    optional.get().isCredentialsNonExpired(), optional.get().isAccountNonLocked(), authorities);
        } else {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
    }
}
