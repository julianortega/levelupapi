package com.jortega.levelup.auth.infrastructure;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String auth = request.getHeader("Authorization");
        if (auth != null) {
            String[] parts = auth.trim().split("\\s+");
            if (parts.length == 2 && parts[0].equalsIgnoreCase("Bearer")) {
                String token = parts[1];
                try {
                    UUID userId = jwtService.verifyAndGetUserId(token);
                    var principal = new UserPrincipal(userId);
                    var authentication = new UsernamePasswordAuthenticationToken(
                            principal, null, java.util.List.of()
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (io.jsonwebtoken.JwtException | IllegalArgumentException ignored) {
                    // token inválido/expirado - no autenticamos
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
