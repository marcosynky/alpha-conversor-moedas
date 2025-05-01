package aula.spring_jwt_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // Definindo o UserDetailsService com usuários em memória
    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("user")
                .password(passwordEncoder().encode("userpassword")) // Senha para o usuário comum
                .roles("USER")
                .build();

        var admin = User.withUsername("admin")
                .password(passwordEncoder().encode("adminpassword")) // Senha para o admin
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin); // Criando o manager com usuários
    }

    // Definindo o password encoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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

    // Configuração de segurança HTTP (Spring Security 6.x)
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // Desabilitar CSRF (não recomendado para produção)
                .authorizeHttpRequests()
                .requestMatchers(SWAGGER_WHITELIST).permitAll() // Permitir acesso ao Swagger
                .requestMatchers("/login", "/register").permitAll() // Permitir login e registro sem autenticação
                .requestMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN") // Apenas admin pode acessar /admin
                .requestMatchers(HttpMethod.GET, "/user").hasRole("USER") // Apenas usuário pode acessar /user
                .anyRequest().authenticated() // Outras rotas necessitam de autenticação
                .and()
                .formLogin() // Configuração para o login com página customizada
                .loginPage("/login") // Página customizada de login
                .permitAll();
    }
}
