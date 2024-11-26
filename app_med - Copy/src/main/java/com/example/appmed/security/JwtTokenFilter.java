package com.example.appmed.security;

import com.example.appmed.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull FilterChain chain
    ) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // Extrai o token JWT do cabeçalho Authorization, se presente
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtTokenProvider.getUsernameFromToken(jwt);
        } else {
            jwt = null;
            username = null;
        }

        // Verifica se o username está presente e se não há autenticação no contexto de segurança
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Long usuarioId = jwtTokenProvider.getUsuarioIdFromToken(jwt);  // Obtém o usuarioId do token

            if (usuarioId == null) {
                throw new IllegalArgumentException("Token inválido: usuário ID não encontrado.");
            }

            usuarioRepository.findById(usuarioId).ifPresent(usuario -> {
                if (jwt != null && jwtTokenProvider.validateToken(jwt)) {
                    String role = jwtTokenProvider.getRoleFromToken(jwt);

                    // Garante que o role tenha o prefixo "ROLE_"
                    if (!role.startsWith("ROLE_")) {
                        role = "ROLE_" + role.toUpperCase();
                    }

                    UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                            usuario.getEmail(), usuario.getSenha(),
                            Collections.singleton(new SimpleGrantedAuthority(role))
                    );

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            });
        }
        chain.doFilter(request, response);
    }
}
