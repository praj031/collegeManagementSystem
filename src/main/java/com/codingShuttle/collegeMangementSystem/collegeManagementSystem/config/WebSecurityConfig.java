package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    //private static final String ADMIN = ;

    //Here we will configure the web security configuration
    //We will use the bean of it.
    //By using this @EnableWebSecurity, we tell the spring boot application that, we will configure our spring boot
    //Security here...

    //Basically u can write here the rules that you want to implement in the authentication
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){

        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/posts").permitAll()
                        .requestMatchers(HttpMethod.POST, "/posts/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated())
                        .csrf(csrfConfig -> csrfConfig.disable())
                        .sessionManagement(sessionConfig -> sessionConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .formLogin(Customizer.withDefaults());


            return httpSecurity.build();
    }


    //This is basically when we want to pass in the user that we create in the application
    @Bean
    UserDetailsService myDetailsService(){
        UserDetails normalUser = User.withUsername("praj").password(passwordEncoder().encode("pass31")).roles("USER").build();

        UserDetails adminUser = User.withUsername("admin").password(passwordEncoder().encode("admin31")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(normalUser,adminUser);

    }

    //This is for password encoder
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //this is basically a layer where all the security chain are there, and we can define as many as we can and use it
    /*

    Here you define the rules what all API you want to authenticate and all.

            httpSecurity
                .authorizeHttpRequests( auth -> auth
                         .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
                -- This is form Login controller, and we are using -CSRF thing to implement the validation


             See these are the rules that is being implemented for authorizations


           .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
           //This means we don't use session id creation



     */


}
