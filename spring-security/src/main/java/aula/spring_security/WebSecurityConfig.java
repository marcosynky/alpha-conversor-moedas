package aula.spring_security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/home", "/login").permitAll()  // Permitir o acesso à página home e login
                        .requestMatchers("/hello").authenticated()  // Necessário login para acessar /hello
                        .requestMatchers("/manager").hasRole("MANAGER")  // Apenas MANAGERS podem acessar a página manager
                        .requestMatchers("/user").hasRole("USER")  // Apenas USERS podem acessar a página user
                        .anyRequest().authenticated()  // Qualquer outra página exige autenticação
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/hello", true)  // Redireciona para /hello após login
                        .successHandler((request, response, authentication) -> {
                            // Verificamos se o usuário tem a role MANAGER
                            if (authentication.getAuthorities().stream()
                                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_MANAGER"))) {
                                // Se for um manager, redireciona para a página /manager
                                response.sendRedirect("/manager");
                            } else if (authentication.getAuthorities().stream()
                                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"))) {
                                // Se for um user, redireciona para a página /user
                                response.sendRedirect("/user");
                            } else {
                                // Caso contrário, redireciona para a página /hello
                                response.sendRedirect("/hello");
                            }
                        })
                )
                .logout((logout) -> logout.permitAll());  // Permitir logout para todos

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Criando o encoder de senha
        PasswordEncoder passwordEncoder = passwordEncoder();

        // Criando os usuários com roles
        UserDetails janeth = User.withUsername("Janeth")
                .password(passwordEncoder.encode("1234"))  // Senha criptografada
                .roles("USER")  // Role USER
                .build();

        UserDetails marcoAntonio = User.withUsername("Marco Antonio")
                .password(passwordEncoder.encode("123"))  // Senha criptografada
                .roles("MANAGER")  // Role MANAGER
                .build();

        // Gerenciador de usuários em memória
        return new InMemoryUserDetailsManager(janeth, marcoAntonio);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
