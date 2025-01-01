package com.project.auth.security;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

/**
 * Created by user on Jul, 2024
 */

@Component
@AllArgsConstructor
public class JwtToUser implements Converter<Jwt, UsernamePasswordAuthenticationToken> {


    private UserDetailsService userDetailsService;

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt source) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(source.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, source, userDetails.getAuthorities());
    }
}
