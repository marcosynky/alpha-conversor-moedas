package aula.spring_jwt_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // Configuração de segurança, substituindo o método 'configure' do Adapter
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/h2-console/**")
                .permitAll()
                .requestMatchers(SWAGGER_WHITELIST)
                .permitAll() // Permite o acesso sem autenticação
                .requestMatchers("/login") // Permite o acesso sem autenticação
                .permitAll()
                .anyRequest().authenticated() // Exige autenticação para todas as outras requisições
                .and();

        http.formLogin();
        http.csrf().disable();
        http.headers().frameOptions().disable();


        return http.build();
    }


    // Lista de URLs permitidas (exemplo: Swagger, H2 console)
    private static final String[] SWAGGER_WHITELIST = {
            "/v2/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };


    // Configuração do serviço de usuários em memória
    @Bean
    public UserDetailsService userDetailsService() {
        // Cria um usuário fixo em memória
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .build();

        return username -> {
            if ("user".equals(username)) {
                return user;  // Retorna o usuário configurado se o nome de usuário for "user"
            }
            throw new RuntimeException("User not found");
        };
    }

    // Define o PasswordEncoder para codificação de senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

