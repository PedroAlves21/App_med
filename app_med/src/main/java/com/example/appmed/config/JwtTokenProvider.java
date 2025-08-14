package com.example.appmed.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // Use uma chave secreta definida por variável de ambiente para maior segurança
    private final Key secretKey;

    public JwtTokenProvider() {
        // A chave secreta é agora obtida da variável de ambiente JWT_SECRET_KEY
        String secretKeyFromEnv = System.getenv("JWT_SECRET_KEY");
        if (secretKeyFromEnv == null || secretKeyFromEnv.isEmpty()) {
            throw new IllegalArgumentException("A chave secreta JWT não foi configurada corretamente.");
        }
        this.secretKey = Keys.hmacShaKeyFor(secretKeyFromEnv.getBytes());
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return (bearerToken != null && bearerToken.startsWith("Bearer ")) ? bearerToken.substring(7) : null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("Token inválido: " + e.getMessage());
            return false;
        }
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String createToken(String userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000); // Expira em 1 dia

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }
}
