package com.example.base.core.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private final SecretKey secretKey = Keys.hmacShaKeyFor("minha-chave-ultra-secreta-de-pelo-menos-256-bits!!!".getBytes());

    @Override
    public String generateToken(Authentication authentication) {
        return Jwts.builder().subject(authentication.getName())
                .claim("authorities", authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority).toList())
                .signWith(secretKey)
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 10min de expiração
                .compact();
    }

    @Override
    public Authentication getAuthentication(String token) {
        try {
            // Nova sintaxe do JJWT 0.12.0+
            var claims = Jwts.parser()
                    .verifyWith(secretKey) // Verifica a assinatura com a sua chave
                    .build()
                    .parseSignedClaims(token) // Faz a leitura do token
                    .getPayload(); // Extrai os dados (claims)

            String username = claims.getSubject();
            var roles = claims.get("authorities", java.util.List.class);

            var authorities = ((java.util.List<?>) roles).stream()
                    .map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority((String) role))
                    .toList();

            return new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(username, null, authorities);
        } catch (Exception e) {
            return null; // Retorna nulo se o token for falso, alterado ou expirado
        }
    }
}
