package com.example.crudapplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(2)
public class AdminSecurityConfiguration {

    @Bean
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http, AdminService adminService) throws Exception{
        http
                .securityMatcher("/admin/**")
                .userDetailsService(adminService)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/login","/admin/register","/admin/save").permitAll()
                        .requestMatchers("/admin/dashboard/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form-> form
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/admin/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler((request, response, authentication) -> {
                            response.sendRedirect("/admin/dashboard");
                        })
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/admin/logout")
                        .logoutSuccessUrl("/admin/login")
                        .permitAll()
                );
        return http.build();
    }
}
