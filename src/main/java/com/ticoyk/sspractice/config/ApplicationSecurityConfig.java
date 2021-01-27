package com.ticoyk.sspractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails ticoyk = User.builder()
            .username("ticoyk")
            .password(passwordEncoder.encode("password"))
            .roles(UserRoles.ADMIN.name())
            .build();

        UserDetails chico = User.builder()
            .username("chico")
            .password(passwordEncoder.encode("password"))
            .roles(UserRoles.STUDENT.name())
            .build();

        return new InMemoryUserDetailsManager(ticoyk, chico);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
        .antMatchers("/api/**").hasRole(UserRoles.STUDENT.name())
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
    }

}
