package com.example.likelion13th.config;

import com.example.likelion13th.jwt.JwtAuthenticationFilter;
import com.example.likelion13th.service.CustomOAuth2UserService;
import com.example.likelion13th.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService; // UserDetailsService DI. 의존성 주입
    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 설정 추가
        http
                .cors((SecurityConfig::corsAllow)) // CORS 설정
                .csrf(AbstractHttpConfigurer::disable) // 비활성화
                .sessionManagement((manager) -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //세션 로그인 안함
                .httpBasic(AbstractHttpConfigurer::disable) // http basic auth 기반 로그인 인증창 뜨지 않게
                .formLogin(AbstractHttpConfigurer::disable) // 기본 로그인 페이지 없애기
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/join", "/login").permitAll() // 모두 허용
                        .requestMatchers("/**").authenticated()) // 인증된 사용자만 허용
//                .oauth2Login(oauth -> oauth
//                        .userInfoEndpoint(userInfo -> userInfo
//                                .userService(customOAuth2UserService)
//                        )
//                )
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/join", "/login",
//                                "/oauth2/**", "/login/oauth2/**",
//                                "h2-console/**", " /error").permitAll() // 회원가입, 로그인은 모두 허용
//                        .anyRequest().authenticated())
//                        // .requestMatchers("/**").authenticated()) // 나머지는 인증된 사용자만 허용
//                        .oauth2Login(oauth -> oauth
//                                .userInfoEndpoint(userInfo -> userInfo
//                                        .userService(customOAuth2UserService)
//                                )
//                        )
                //.formLogin(Customizer.withDefaults())
                //.logout(Customizer.withDefaults())
                .userDetailsService(customUserDetailsService);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // 해당 필터 전에 jwtFilter가 걸리도록
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static void corsAllow(CorsConfigurer<HttpSecurity> corsConfigurer) {
        corsConfigurer.configurationSource(request -> {
            CorsConfiguration configuration = new CorsConfiguration();

            configuration.setAllowedMethods(Collections.singletonList("*")); // 모든 메서드 허용
            configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // 프론트에서 오는 요청 허용
            configuration.setAllowedHeaders(Collections.singletonList("*")); // 모든 헤더 허용
            configuration.setAllowCredentials(true);
            configuration.setMaxAge(3600L); // 1시간(3600초) 동안 오는 요청이 처리됨

            return configuration;
        });
    }
}
