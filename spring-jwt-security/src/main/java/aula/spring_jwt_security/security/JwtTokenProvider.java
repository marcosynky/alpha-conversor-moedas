package aula.spring_jwt_security.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String SECRET_KEY = "your-secret-key"; // Use algo mais seguro

    // Gera o token JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Expira em 1 dia
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}