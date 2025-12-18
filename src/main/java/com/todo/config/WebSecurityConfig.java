package com.todo.config;

import com.todo.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity // Spring security 활성화
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // WebMvcConfig에서 이미 설정했으므로 기본 cors 설정
                .cors(withDefaults())
                // jwt토큰을 사용하므로 csrf비활성화 -> localstorage에 저장시 비활성화 아니면 활성화
                .csrf(csrfConfig -> csrfConfig.disable())
                // http 기본 인증 비활성화
                .httpBasic(httpBasicCofig -> httpBasicCofig.disable())
                // Session 기반이 아님을 선언
                .sessionManagement(sessionManagementConfig -> sessionManagementConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // /와 /auth/**,css ,js image ,h2-console 경로는 인증 안해도 된다.                                                / 그 외 모든 경로는 인증해야함
                .authorizeHttpRequests(auth -> auth.requestMatchers("/", "/auth/**").permitAll().anyRequest().authenticated())
                .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.getOrBuild();
    }
}
