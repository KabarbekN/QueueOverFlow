package io.nurgissa.queueoverflow.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import java.nio.file.AccessDeniedException;

import static io.nurgissa.queueoverflow.models.enums.Permission.*;
import static io.nurgissa.queueoverflow.models.enums.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.
                                requestMatchers(
                                         "/api/v1/auth/**",
//                                        "/api/v1/tag/**",
//                                        "/api/v1/user/**",
                                        "/v3/api-docs",
                                        "/v3/api-docs/**",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html"
                                )
                                .permitAll()
                                .requestMatchers(GET, "/api/v1/tag/**").permitAll()
                                .requestMatchers( "/api/v1/question/**").permitAll()
                                .requestMatchers(GET, "/api/v1/answer/**").permitAll()
                                .requestMatchers(GET, "/api/v1/comment/**").permitAll()

                                .requestMatchers("/api/v1/user/**").hasAnyRole(USER.name(), ADMIN.name(), MODERATOR.name())
//                                .requestMatchers(GET, "/api/v1/user/**").hasAnyRole(USER.name())
//                                .requestMatchers(POST, "/api/v1/user/**").hasAnyRole(USER.name())
//                                .requestMatchers(GET, "/api/v1/user/**").hasAnyRole(USER.name())
//                                .requestMatchers(GET, "/api/v1/user/**").hasAnyRole(USER.name())
                                // For future functionality
                                .requestMatchers( DELETE, "/api/v1/answer/**").hasAnyRole(USER.name(), ADMIN.name(), MODERATOR.name())
                                .requestMatchers( DELETE, "/api/v1/comment/**").hasAnyRole(USER.name(), ADMIN.name(), MODERATOR.name())
                                .requestMatchers( DELETE, "/api/v1/question/**").hasAnyRole(USER.name(), ADMIN.name(), MODERATOR.name())



                                .requestMatchers("/api/v1/tag/**").hasAnyRole(USER.name(),ADMIN.name(), MODERATOR.name())
//                                .requestMatchers(GET, "/api/v1/tag/**").hasAnyRole(USER.name(), MODERATOR.name(), ADMIN.name())
                                .requestMatchers(POST,"/api/v1/tag/**").hasAnyAuthority(ADMIN_CREATE.name(), MODERATOR_CREATE.name())
                                .requestMatchers(PUT,"/api/v1/tag/**").hasAnyAuthority(ADMIN_UPDATE.name(), MODERATOR_UPDATE.name())
                                .requestMatchers(DELETE,"/api/v1/tag/**").hasAnyAuthority(ADMIN_DELETE.name(), MODERATOR_DELETE.name())


                                .requestMatchers("/api/v1/moderator/**").hasAnyRole(ADMIN.name(), MODERATOR.name())
                                .requestMatchers(GET, "/api/v1/moderator/**").hasAnyAuthority(ADMIN_READ.name(), MODERATOR_READ.name())
                                .requestMatchers(POST,"/api/v1/moderator/**").hasAnyAuthority(ADMIN_CREATE.name(), MODERATOR_CREATE.name())
                                .requestMatchers(PUT,"/api/v1/moderator/**").hasAnyAuthority(ADMIN_UPDATE.name(), MODERATOR_UPDATE.name())
                                .requestMatchers(DELETE,"/api/v1/moderator/**").hasAnyAuthority(ADMIN_DELETE.name(), MODERATOR_DELETE.name())

                                .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
                                .requestMatchers(GET,"/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
                                .requestMatchers(POST,"/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
                                .requestMatchers(PUT,"/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
                                .requestMatchers(DELETE,"/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())

                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext())));
        return http.build();
    }

}
