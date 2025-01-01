package com.project.auth.security;

import com.project.auth.entity.ParamEntity;
import com.project.auth.service.ParamService;
import com.project.commons.model.enums.ParamTypeConstant;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by user on Jul, 2024
 */

@Slf4j
@Component
public class TokenProvider {


    private JwtEncoder jwtEncoder;

    private JwtDecoder jwtDecoder;

    @Qualifier("jwtRefreshTokenEncoder")
    private JwtEncoder refreshTokenEncoder;

    private UserDetailsService userDetailsService;

    private ParamService paramService;

    @Value("${jwt.issuer}")
    private String jwtIssuer;

    @Value("${jwt.accessTokenExp}")
    private String jwtAccessTokenExp;

    @Value("${jwt.refreshTokenExp}")
    private String jwtRefreshTokenExp;

    @Autowired
    public TokenProvider(JwtEncoder jwtEncoder, JwtEncoder refreshTokenEncoder, JwtDecoder jwtDecoder, UserDetailsService userDetailsService, ParamService paramService) {
        this.jwtEncoder = jwtEncoder;
        this.refreshTokenEncoder = refreshTokenEncoder;
        this.jwtDecoder = jwtDecoder;
        this.userDetailsService = userDetailsService;
        this.paramService = paramService;
    }

    public String createAccessToken(Authentication authentication) {

        JwtClaimsSet claimsSet = buildClaimsSet(authentication, true);

        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public String createRefreshToken(Authentication authentication) {

        JwtClaimsSet claimsSet = buildClaimsSet(authentication, false);

        return refreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    private JwtClaimsSet buildClaimsSet(Authentication authentication, boolean isGenerateAccessToken) {

        Instant now = Instant.now();

        String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        long expiredSecond = Long.parseLong(jwtAccessTokenExp);

        Optional<ParamEntity> optIssuer = paramService.findByParamNameAndEmbeddedEntity_Active(ParamTypeConstant.ISSUER_JWT.name(), true);
        optIssuer.ifPresent(paramEntity -> jwtIssuer = paramEntity.getParamValue());

        if (isGenerateAccessToken) {
            Optional<ParamEntity> optAccess = paramService.findByParamNameAndEmbeddedEntity_Active(ParamTypeConstant.EXPIRES_JWT_ACCESS.name(), true);
            if (optAccess.isPresent() && StringUtils.hasText(optAccess.get().getParamValue())) {
                expiredSecond = Long.parseLong(optAccess.get().getParamValue());
            }
        } else {
            Optional<ParamEntity> optRefresh = paramService.findByParamNameAndEmbeddedEntity_Active(ParamTypeConstant.EXPIRES_JWT_REFRESH.name(), true);
            if (optRefresh.isPresent() && StringUtils.hasText(optRefresh.get().getParamValue())) {
                expiredSecond = Long.parseLong(optRefresh.get().getParamValue());
            }
        }

        return JwtClaimsSet.builder()
                .issuer(jwtIssuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiredSecond))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getSubject();
    }

    public long getRemainingExpiredFromToken(String token) {
        Instant now = Instant.now();
        Instant expiresAt = getExpiresAtFromToken(token);
        Duration duration = Duration.between(now, expiresAt);
        return duration.getSeconds();
    }

    public Instant getExpiresAtFromToken(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getExpiresAt();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token);
            Instant expiresAt = jwt.getExpiresAt();
            if (expiresAt != null) {
                return expiresAt.isAfter(Instant.now());
            }
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
        return false;
    }

}
