package aula.spring_jwt_security.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class JwtAuthenticationFilter implements Filter {

    private final String SECRET_KEY = "your-secret-key"; // Chave secreta para validar o JWT (troque por algo mais seguro)

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Método de inicialização, não necessário para nossa implementação
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Verifica se há o token no cabeçalho da requisição
        String token = ((HttpServletRequest) request).getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove o "Bearer " do token

            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(token)
                        .getBody();

                // Cria um objeto de autenticação com base nos dados do token
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        claims.getSubject(), null, null); // claims.getSubject() normalmente é o nome do usuário

                // Define o contexto de segurança com o usuário autenticado
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                // Caso o token seja inválido ou tenha algum problema, ignora a requisição
                ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }

        // Continua a execução da cadeia de filtros
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Método de destruição, não necessário para nossa implementação
    }
}

