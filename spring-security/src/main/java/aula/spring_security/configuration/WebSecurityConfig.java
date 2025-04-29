package aula.spring_security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/home", "/register", "/login").permitAll()  // Permite acesso às páginas home, register, login
                        .requestMatchers("/hello").authenticated()  // Exige login para acessar /hello
                        .requestMatchers("/manager").hasRole("MANAGER")  // Somente quem tem a role MANAGER pode acessar /manager
                        .requestMatchers("/user").hasRole("USER")  // Somente quem tem a role USER pode acessar /user
                        .anyRequest().authenticated()  // Qualquer outra página exige autenticação
                )
                .formLogin((form) -> form
                        .loginPage("/login")  // Página de login personalizada
                        .permitAll()
                        .defaultSuccessUrl("/home", true)  // Após login, redireciona para home ou página inicial
                )
                .logout((logout) -> logout.permitAll());  // Permite logout para todos

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        String password = passwordEncoder().encode("password");

        UserDetails user = User.builder()
                .username("user")
                .password(password)
                .roles("USER")
                .build();

        UserDetails manager = User.builder()
                .username("manager")
                .password(password)
                .roles("MANAGER")
                .build();

        return new InMemoryUserDetailsManager(user, manager);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Usando BCrypt para codificação de senha
    }
}

