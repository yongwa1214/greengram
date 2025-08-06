package kr.co.wikibook.greengram2.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

/*
@Configuration - bean 등록, Bean 메소드가 있다.
Bean 메소드는 무조건 싱글톤으로 처리된다.
 */
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final TokenAuthenticationEntryPoint tokenAuthenticationEntryPoint;

    //Bean 메소드
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //람다식
        return http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //security가 session을 사용하지 않는다.
                   .httpBasic(httpBasicSpec -> httpBasicSpec.disable()) //시큐리티가 제공해주는 인증 처리 -> 사용 안 함
                   .formLogin(formLoginSpec -> formLoginSpec.disable()) //시큐리티가 제공해주는 인증 처리 -> 사용 안 함
                   .csrf(csrfSpec -> csrfSpec.disable()) // BE - csrf라는 공격이 있는데 공격을 막는 것이 기본으로 활성화 되어 있는데
                                                        // 세션을 이용한 공격이다. 세션을 어차피 안 쓰니까 비활성화
                    .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource())) // ⭐️⭐️⭐️
                   .authorizeHttpRequests(req -> req.requestMatchers("/api/v1/cart").authenticated()
                                                    .requestMatchers( "/api/v1/order").authenticated()
                                                    .requestMatchers(HttpMethod.POST, "/api/v1/item").hasRole("USER_2")
                                                    .anyRequest().permitAll()
                   )

                   .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                   .exceptionHandling(e -> e.authenticationEntryPoint(tokenAuthenticationEntryPoint))
                   .build();
    }


    // ⭐️ CORS 설정
    CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedOriginPatterns(Collections.singletonList("*")); // ⭐️ 허용할 origin
            config.setAllowCredentials(true);
            return config;
        };
    }

}