package com.minha.mart.Config;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig  {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 설정, 필요에 따라
                .csrf().disable()
                // 기존 설정...
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/**").permitAll() //  모든 경로 허용
                        .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
                )
                .formLogin(formLogin -> formLogin
                                .loginPage("/login") // 사용자 정의 로그인 페이지
                                .defaultSuccessUrl("/main") // 로그인 성공 시 리다이렉트할 기본 경로
                        // 기타 로그인 관련 설정...
                )
                .logout(logout -> logout
                                .logoutSuccessUrl("/main") // 로그아웃 성공 시 리다이렉트할 경로
                        // 기타 로그아웃 관련 설정...
                )
        ;
        return http.build();
    }
}
