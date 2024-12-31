package com.project.auth.controller;

import com.project.auth.model.request.LoginRequest;
import com.project.auth.model.response.LoginResponse;
import com.project.auth.security.TokenProvider;
import com.project.commons.controller.BaseController;
import com.project.commons.exception.AuthenticationExceptionHandler;
import com.project.commons.model.enums.MethodMessage;
import com.project.commons.model.enums.StatusConstant;
import com.project.commons.model.response.ObjectApiResponse;
import com.project.commons.model.response.ObjectMessageResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 * Created by user on Jul, 2024
 */


@RestController
public class AuthController extends BaseController {

    Logger log = LoggerFactory.getLogger(getClass());

    private TokenProvider tokenProvider;

    @Qualifier("jwtRefreshTokenAuthProvider")
    JwtAuthenticationProvider refreshTokenAuthProvider;

    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(TokenProvider tokenProvider, JwtAuthenticationProvider refreshTokenAuthProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.refreshTokenAuthProvider = refreshTokenAuthProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<ObjectApiResponse> login(@RequestBody LoginRequest userLogin) throws IllegalAccessException {
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                userLogin.getUsername(),
                                userLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        log.info("Token requested for user :{}", authentication.getAuthorities());

        String token = tokenProvider.createAccessToken(authentication);

        LoginResponse response = new LoginResponse();
        response.setUsername(userLogin.getUsername());
        response.setAccessToken(token);
        response.setExpiredAccessToken(tokenProvider.getRemainingExpiredFromToken(token));
        String refreshToken;
        if (authentication.getCredentials() instanceof Jwt jwt) {
            Instant now = Instant.now();
            Instant expiresAt = jwt.getExpiresAt();
            Duration duration = Duration.between(now, expiresAt);
            long daysUntilExpired = duration.toDays();
            if (daysUntilExpired < 7) {
                refreshToken = tokenProvider.createRefreshToken(authentication);
            } else {
                refreshToken = jwt.getTokenValue();
            }
        } else {
            refreshToken = tokenProvider.createRefreshToken(authentication);
        }
        response.setRefreshToken(refreshToken);
        response.setExpiredRefreshToken(tokenProvider.getRemainingExpiredFromToken(refreshToken));
        log.info("Token requested for user :{}", token);

        return new ResponseEntity<>(responseApi(response), HttpStatus.OK);
    }

    @PostMapping("/token")
    public ResponseEntity<ObjectApiResponse> token(@RequestBody LoginRequest request) {
        ObjectMessageResponse msg = new ObjectMessageResponse();
        try {
            Authentication authentication = refreshTokenAuthProvider.authenticate(new BearerTokenAuthenticationToken(request.getRefreshToken()));
            Jwt jwt = (Jwt) authentication.getCredentials();
            // check if present in db and not revoked, etc

            String refreshToken = tokenProvider.createAccessToken(authentication);

            LoginResponse response = new LoginResponse();
            response.setUsername(request.getUsername());
            response.setAccessToken(refreshToken);
            response.setExpiredAccessToken(tokenProvider.getRemainingExpiredFromToken(refreshToken));
            return new ResponseEntity<>(responseApi(response), HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new AuthenticationExceptionHandler(e.getMessage(), e);
        } catch (Exception e) {
            msg.setMessage(e.getMessage());
            return new ResponseEntity<>(responseApi(msg), HttpStatus.OK);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ObjectApiResponse> logout(HttpSession session, HttpServletRequest request,
                             HttpServletResponse response,
                             @RegisteredOAuth2AuthorizedClient("messaging-client-oidc") OAuth2AuthorizedClient authorizedClient)
            throws IOException {

        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            auth.setAuthenticated(false);
            SecurityContextHolder.clearContext();
            for (Cookie cookie : request.getCookies()) {
                String cookieName = cookie.getName();
                log.info("cookie name={}", cookieName);
                Cookie cookieToDelete = new Cookie(cookieName, null);
                cookieToDelete.setPath(request.getContextPath() + "/");
                cookieToDelete.setMaxAge(0);
                response.addCookie(cookieToDelete);
            }
            SecurityContextHolder.getContext().setAuthentication(null);
        }

        ObjectMessageResponse msg = new ObjectMessageResponse();
        msg.setMessage("Successfully Logout");

        ObjectApiResponse dto = new ObjectApiResponse();
        dto.setStatus(StatusConstant.SUCCESS.getEn());
        dto.setMessage(StatusConstant.SUCCESS.getEn());
        dto.setData(msg);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
