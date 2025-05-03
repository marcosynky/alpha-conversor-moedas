package aula.spring_jwt_security.security;

import aula.spring_jwt_security.model.User;
import aula.spring_jwt_security.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider; // Injetando o provedor de tokens JWT

    // Configuração de segurança
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        .requestMatchers("/h2-console/**").permitAll() // Permitindo acesso ao H2 console
                        .anyRequest().authenticated() // Requer autenticação para qualquer outra requisição
                )
                .formLogin((form) -> form
                        .loginProcessingUrl("/login")
                        .successHandler((req, res, auth) -> res.sendRedirect("/"))
                        .failureHandler((req, res, exc) -> res.setStatus(401))
                )
                .csrf().disable() // Desabilitando o CSRF para permitir o H2 console no navegador
                .headers().frameOptions().disable(); // Desabilitando proteção contra frames para permitir o H2 console

        // Adicionando o filtro JWT
        http.addFilterBefore(new aula.spring_jwt_security.security.JwtAuthFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Lista de URLs permitidas para Swagger, H2 console, etc.
    private static final String[] SWAGGER_WHITELIST = {
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };

    // Configuração do UserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            return (org.springframework.security.core.userdetails.UserDetails) user;
        };
    }

    // Configuração do AuthenticationManager, essencial para o Spring Security
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    // Define o PasswordEncoder para codificação e verificação de senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usando BCrypt para criptografar as senhas
    }

    // Configuração do DaoAuthenticationProvider
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
