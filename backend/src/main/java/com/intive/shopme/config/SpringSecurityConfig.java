package com.intive.shopme.config;

import com.intive.shopme.tokens.AuthenticationRequestMatcher;
import com.intive.shopme.tokens.UnauthorizedEntryPoint;
import com.intive.shopme.tokens.authentication.JwtAuthenticationProvider;
import com.intive.shopme.tokens.authentication.TokenAuthenticationFailureHandler;
import com.intive.shopme.tokens.authentication.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

import static com.intive.shopme.config.ApiUrl.LOGIN;
import static com.intive.shopme.config.AppConfig.REST_ENTRY_POINT;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Bcrypt log rounds to use, between 4 and 31
     */
    private static final int HASHING_STRENGTH = 11;


    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    public SpringSecurityConfig(JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }


    private TokenAuthenticationFilter getAuthenticationFilter() throws Exception {
        final List<String> pathsToSkip = Arrays.asList(LOGIN);
        final AuthenticationRequestMatcher matcher = new AuthenticationRequestMatcher(pathsToSkip);
        final TokenAuthenticationFilter filter =
                new TokenAuthenticationFilter(new TokenAuthenticationFailureHandler(), matcher);
        filter.setAuthenticationManager(authenticationManager());

        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers(LOGIN).permitAll()
                .and().authorizeRequests().antMatchers(REST_ENTRY_POINT).authenticated()
                .and().headers().frameOptions().disable()
                .and().addFilterBefore(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(HASHING_STRENGTH);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
