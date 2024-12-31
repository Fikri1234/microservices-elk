package com.project.auth.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.project.auth.configuration.AppJwtProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.sql.Date;
import java.time.Instant;
import java.util.stream.Collectors;

/**
 * Created by user on Jul, 2024
 */

@Component
@RequiredArgsConstructor
public class JwtProvider {

//    private final AppJwtProperties appJwtProperties;
//
//    private UserDetailsService userDetailsService;
//
//
//    public String generateJWT(Authentication authentication) {
//        var key = appJwtProperties.getKey();
//        var algorithm = appJwtProperties.getAlgorithm();
//
//        var header = new JWSHeader(algorithm);
//        var claimsSet = buildClaimsSet(authentication);
//
//        var jwt = new SignedJWT(header, claimsSet);
//
//        try {
//            var signer = new MACSigner(key);
//            jwt.sign(signer);
//        } catch (JOSEException e) {
//            throw new RuntimeException("Unable to generate JWT", e);
//        }
//
//        return jwt.serialize();
//    }
//
//    private JWTClaimsSet buildClaimsSet(Authentication authentication) {
//
//        String scope = authentication.getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(" "));
//
//        var issuer = appJwtProperties.getIssuer();
//        var issuedAt = Instant.now();
//        var expirationTime = issuedAt.plus(appJwtProperties.getExpiresIn());
//
//        var builder = new JWTClaimsSet.Builder()
//                .expirationTime(Date.from(expirationTime))
//                .subject(authentication.getName())
//                .issuer(issuer)
//                .claim("scope", scope).build();
//
//        return builder;
//    }
//
//    public Authentication getAuthentication(String token) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }
//
//    public String getUsername(String token) {
//        Jwt jwt = NimbusJwtDecoder.withSecretKey(new SecretKeySpec(appJwtProperties.getKey().getEncoded(), appJwtProperties.getAlgorithm().getName())).build().decode(token);
//        return jwt.getSubject();
//    }
//
//    public String resolveToken(HttpServletRequest req) {
//        String bearerToken = req.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwt jwt = NimbusJwtDecoder.withSecretKey(new SecretKeySpec(appJwtProperties.getKey().getEncoded(), appJwtProperties.getAlgorithm().getName())).build().decode(token);
//            return jwt.getExpiresAt().isAfter(Instant.now());
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }
//    }
}
