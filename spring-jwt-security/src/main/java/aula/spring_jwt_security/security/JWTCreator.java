package aula.spring_jwt_security.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.MalformedJwtException;

import java.util.List;
import java.util.stream.Collectors;

public class JWTCreator {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

    // Método para criar o JWT
    public static String create(String prefix, String key, JWTObject jwtObject) {
        String token = Jwts.builder()
                .setSubject(jwtObject.getSubject())  // Define o subject
                .setIssuedAt(jwtObject.getIssueAt())  // Define a data de emissão
                .setExpiration(jwtObject.getExpiration())  // Define a data de expiração
                .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles()))  // Define os roles (autoridades)
                .signWith(SignatureAlgorithm.HS512, key)  // Assina o token com o algoritmo HS512 e a chave
                .compact();  // Cria o token compactado

        return prefix + " " + token;  // Retorna o token com o prefixo (ex: Bearer <token>)
    }

    // Método para criar um objeto JWTObject a partir de um token
    public static JWTObject create(String token, String prefix, String key) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, JwtException {
        JWTObject object = new JWTObject();
        token = token.replace(prefix, "");  // Remove o prefixo (ex: Bearer)
        Claims claims = Jwts.parser()
                .setSigningKey(key)  // Define a chave de assinatura
                .parseClaimsJws(token)  // Analisa o JWT e obtém as claims
                .getBody();  // Obtém o corpo do JWT (contém as informações do token)

        object.setSubject(claims.getSubject());  // Define o subject do JWTObject
        object.setIssueAt(claims.getIssuedAt());  // Define a data de emissão
        object.setExpiration(claims.getExpiration());  // Define a data de expiração
        object.setRoles((List<String>) claims.get(ROLES_AUTHORITIES));  // Define os roles (autoridades)

        return object;  // Retorna o JWTObject com os dados do token
    }

    // Método para garantir que os roles tenham o prefixo "ROLE_"
    private static List<String> checkRoles(List<String> roles) {
        return roles.stream()
                .map(role -> "ROLE_".concat(role.replace("ROLE_", "")))  // Adiciona "ROLE_" se não estiver presente
                .collect(Collectors.toList());  // Retorna a lista transformada
    }
}
