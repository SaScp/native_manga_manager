package ru.alex.manga_manager.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class DeniedRequestFilter extends OncePerRequestFilter {

    private AuthenticationEntryPoint authenticationEntryPoint =
            (request, response, authException) -> {
                response.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer");
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED");
            };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getHeader(HttpHeaders.USER_AGENT).equals("curl")) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "curl - user agent is illegal");
        }

        filterChain.doFilter(request, response);
    }
}
