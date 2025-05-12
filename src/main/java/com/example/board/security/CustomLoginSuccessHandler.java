package com.example.board.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            org.springframework.security.core.Authentication authentication) throws IOException, ServletException {

        AuthMemberDTO AuthMemberDTO = (AuthMemberDTO) authentication.getPrincipal();
        log.info("CostomLoginSucessHandler {}", AuthMemberDTO);

        List<String> roleNames = new ArrayList<>();
        AuthMemberDTO.getAuthorities().forEach(auth -> {
            roleNames.add(auth.getAuthority());
        });

        log.info("roleNames {}", roleNames);

        if (roleNames.contains("ROLE_ADMIN")) {
            response.sendRedirect("/sample/admin");
            return;
        }

        if (roleNames.contains("ROLE_USER") || roleNames.contains("ROLE_MANAGER")) {
            response.sendRedirect("/sample/member");
            return;
        }
        response.sendRedirect("/");
    }

}
