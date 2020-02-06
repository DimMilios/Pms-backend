package com.pms.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import com.google.common.net.HttpHeaders;

/**
 * The type Jwt config.
 */
@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;

    /**
     * Instantiates a new Jwt config.
     */
    public JwtConfig() {
    }

    /**
     * Gets secret key.
     *
     * @return the secret key
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * Sets secret key.
     *
     * @param secretKey the secret key
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * Gets token prefix.
     *
     * @return the token prefix
     */
    public String getTokenPrefix() {
        return tokenPrefix;
    }

    /**
     * Sets token prefix.
     *
     * @param tokenPrefix the token prefix
     */
    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    /**
     * Gets token expiration after days.
     *
     * @return the token expiration after days
     */
    public Integer getTokenExpirationAfterDays() {
        return tokenExpirationAfterDays;
    }

    /**
     * Sets token expiration after days.
     *
     * @param tokenExpirationAfterDays the token expiration after days
     */
    public void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
        this.tokenExpirationAfterDays = tokenExpirationAfterDays;
    }

    /**
     * Gets authorization header.
     *
     * @return the authorization header
     */
    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
