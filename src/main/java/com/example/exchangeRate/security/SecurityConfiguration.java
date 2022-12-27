package com.example.exchangeRate.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.exchangeRate.security.ApplicationUserRole.ADMIN;
import static com.example.exchangeRate.security.ApplicationUserRole.USER;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/api/v1/dailyrate/{dailyRateId}").hasRole(ADMIN.name()) // Admin should be able to delete
                .antMatchers(HttpMethod.PUT, "/api/v1/dailyrate/{dailyRateId}").hasRole(ADMIN.name()) // Admin should be able to update
                .antMatchers(HttpMethod.POST, "/api/v1/dailyrate").hasAnyRole(ADMIN.name()) // Admin should be able to add rate.

                .antMatchers("/api/v1/dailyrate").hasAnyRole(ADMIN.name(), USER.name()) // All 2 users should be able to get all rates.
                .antMatchers("/api/v1/dailyrate/{date}").hasAnyRole(ADMIN.name(), USER.name()) // All 2 users should be able to get a rate by date
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user =
                User.withUsername("user")
                        .password(encoder.encode("password"))
                        .authorities(USER.getGrantedAuthorities())
                        .build();
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("password123"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
