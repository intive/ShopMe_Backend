package com.intive.shopme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Bcrypt log rounds to use, between 4 and 31
     */
    private static final int HASHING_STRENGTH = 11;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // TODO to remove when final security solution implemented
        // this is temporary workaround to disable Spring Security
        // because implementing user authentication and token management
        // was out of scope of this task (password hashing only)
        // but enabling Spring Security (required to have BCrypt crypto library)
        // turns on default security policies (which means blocking access to our endpoints)
        http.csrf().disable().authorizeRequests().antMatchers("/").permitAll();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(HASHING_STRENGTH);
    }
}
