package com.prime_cell.back_end.infra.security;

import com.prime_cell.back_end.infra.security.TokenService;
import com.prime_cell.back_end.models.User;
import com.prime_cell.back_end.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        System.out.println("Token recebido: " + token);

        if (token != null) {
            var login = tokenService.validateToken(token);
            System.out.println("Login após validação: " + login);

            if (login != null) {
                User user = userRepository.findByEmail(login)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                System.out.println("Usuário encontrado: " + user.getEmail());

                var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
                var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        System.out.println("Header de autorização: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.substring(7); // Use substring em vez de replace
    }
}