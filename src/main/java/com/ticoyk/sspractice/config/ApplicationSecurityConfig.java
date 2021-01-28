package com.ticoyk.sspractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.ticoyk.sspractice.config.UserRoles.ADMIN;
import static com.ticoyk.sspractice.config.UserRoles.ADMINTRAINEE;
import static com.ticoyk.sspractice.config.UserRoles.STUDENT;
import static com.ticoyk.sspractice.config.UserRolePermission.COURSE_WRITE;

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
            .authorities(ADMIN.getGrantedAuthorities())
            .build();

        UserDetails chico = User.builder()
            .username("chico")
            .password(passwordEncoder.encode("password"))
            .authorities(STUDENT.getGrantedAuthorities())
            .build();

        UserDetails chicovisk = User.builder()
            .username("chicovisk")
            .password(passwordEncoder.encode("password"))
            .authorities(ADMINTRAINEE.getGrantedAuthorities())
            .build();
        return new InMemoryUserDetailsManager(ticoyk, chico, chicovisk);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
        .antMatchers("/api/**").hasRole(STUDENT.name())
        .antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
        .antMatchers(HttpMethod.POST, "/management/api/**").hasAnyAuthority(COURSE_WRITE.getPermission())
        .antMatchers(HttpMethod.PUT, "/management/api/**").hasAnyAuthority(COURSE_WRITE.getPermission())
        .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAnyAuthority(COURSE_WRITE.getPermission())
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
    }

}
