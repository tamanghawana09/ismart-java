package com.example.crudapplication.Configuration;

import com.example.crudapplication.Service.CustomerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@Order(1)
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomerUserDetailsService customerUserDetailsService) throws Exception{
        http
                .securityMatcher("/**")
                .userDetailsService(customerUserDetailsService)
                .authorizeHttpRequests(auth -> auth
                        // public endpoints
                        .requestMatchers("/","/login","/register","/saveUser").permitAll()
                        .requestMatchers("/dashboard/**").hasRole("USER")
                        .anyRequest().authenticated()
                )

                .formLogin(form->form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler((request,response,authentication) ->{
                            response.sendRedirect("/dashboard");
                        })
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .permitAll()
                );


        return http.build();
    }
}
