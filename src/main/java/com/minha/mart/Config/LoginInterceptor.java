package com.minha.mart.Config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
 /*   @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginID") == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }*/
/* @Override
 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
     HttpSession session = request.getSession(false);
     if (session == null || session.getAttribute("loginID") == null) {
         response.sendRedirect("/member/login"); // 로그인 안된 사용자는 로그인 페이지로 리디렉션
         return false;
     }
     return true;
 }*/
}
