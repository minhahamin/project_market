package com.minha.mart.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  /*  @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**") // 모든 경로에 적용
                .excludePathPatterns("/member/login", "/member/join"); // 특정 경로 제외
    }*/
  /*@Autowired
  private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/member/mypage", "/member/infoupdate/**") // 로그인이 필요한 경로
                .excludePathPatterns("/member/login", "/member/join", "/member/logout"); // 로그인이 필요없는 경로
    }*/
}

