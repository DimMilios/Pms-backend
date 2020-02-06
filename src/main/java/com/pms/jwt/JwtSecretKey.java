package com.pms.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

/**
 * The type Jwt secret key.
 */
@Configuration
public class JwtSecretKey {

    private final JwtConfig jwtConfig;

    /**
     * Instantiates a new Jwt secret key.
     *
     * @param jwtConfig the jwt config
     */
    @Autowired
    public JwtSecretKey(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    /**
     * Secret key secret key.
     *
     * @return the secret key
     */
    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
    }
}
