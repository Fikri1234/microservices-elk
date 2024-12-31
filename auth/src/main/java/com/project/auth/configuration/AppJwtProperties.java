package com.project.auth.configuration;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.crypto.SecretKey;
import java.time.Duration;

/**
 * Created by user on Jul, 2024
 */

//@Getter
//@Setter
//@Validated
//@ConfigurationProperties(prefix = "app.jwt")
public class AppJwtProperties {

//    @NotNull
//    private SecretKey key;
//
//    @NotEmpty
//    private String issuer;
//
//    @NotNull
//    private JWSAlgorithm algorithm;
//
//    @NotNull
//    @DurationMin(seconds = 1)
//    private Duration expiresIn;
//
//    public void setAlgorithm(String algorithm) {
//        this.algorithm = JWSAlgorithm.parse(algorithm);
//    }
//
//    public void setKey(String key) {
//        var jwk = new OctetSequenceKey.Builder(key.getBytes())
//                .algorithm(algorithm)
//                .build();
//
//        this.key = jwk.toSecretKey();
//    }
}
