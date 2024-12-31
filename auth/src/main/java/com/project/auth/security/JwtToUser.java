package com.project.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
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
public class JwtToUser implements Converter<Jwt, UsernamePasswordAuthenticationToken> {


    private UserDetailsService userDetailsService;

    @Autowired
    public JwtToUser(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt source) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(source.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, source, userDetails.getAuthorities());
    }
}
