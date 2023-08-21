package com.test.keycloakdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Autowired
    JwtAuthConverter jwtAuthConverter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                //Here's how a CSRF attack works:
                //
                //The user is authenticated to a legitimate website (e.g., an online banking site) and has an active session.
                //
                //The user visits a different website (the malicious site) which includes malicious code, typically in the form of an image, a link, or a script.
                //
                //The malicious code on the attacker's site sends a request to the legitimate site on behalf of the authenticated user. Since the user is already authenticated, the legitimate site trusts the request and performs the action (such as changing the user's password, transferring money, etc.) without the user's explicit consent.
                //
                //The user remains unaware that an unauthorized action was taken on their behalf.
                //It has to be disabled to get aligned with jwt authentication

                .csrf((csrf) -> csrf.disable())


                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .anyRequest().authenticated()
                )


                .oauth2ResourceServer((oauth2ResourceServer) ->
                        oauth2ResourceServer
                                .jwt((jwt) ->
                                        jwt
                                                .jwtAuthenticationConverter(jwtAuthConverter))


                )

                // In stateless authentication, the concept of traditional sessions is intentionally avoided.
                // This approach is often used in scenarios like JWT-based authentication, where the server doesn't store any user-related information on its side.
                // Instead, all the necessary information to authenticate a user is contained within the token itself.
                // Does not maintain the track record of the user.
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(STATELESS)


                );





        return http.build();
    }
}