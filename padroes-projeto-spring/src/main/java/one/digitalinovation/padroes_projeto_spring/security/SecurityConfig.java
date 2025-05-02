package one.digitalinovation.padroes_projeto_spring.security;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui/**", "/webjars/**").permitAll() // Permite o acesso às rotas do Swagger
                .anyRequest().authenticated() // Exige autenticação para todas as outras rotas
                .and()
                .formLogin().permitAll() // Permite login via formulário
                .and()
                .logout().permitAll(); // Permite o logout

        return http.build();
    }
}
