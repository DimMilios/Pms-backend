package com.pms.security;

import com.pms.jwt.JwtConfig;
import com.pms.jwt.JwtTokenVerifier;
import com.pms.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

/**
 * The type App security config.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    /**
     * Instantiates a new App security config.
     *
     * @param passwordEncoder the password encoder
     * @param userService     the user service
     * @param secretKey       the secret key
     * @param jwtConfig       the jwt config
     */
    @Autowired
    public AppSecurityConfig(PasswordEncoder passwordEncoder,
                             UserService userService,
                             SecretKey secretKey,
                             JwtConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll();

        http.headers().frameOptions().disable();

        http
                .authorizeRequests()
                .and()
                .csrf().disable()
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig),JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").permitAll() //.hasRole(ADMIN.name())
                .anyRequest()
                .authenticated();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * Dao authentication provider dao authentication provider.
     *
     * @return the dao authentication provider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
