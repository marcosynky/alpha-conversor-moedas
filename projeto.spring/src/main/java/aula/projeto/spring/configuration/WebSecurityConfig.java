package aula.projeto.spring.configuration;

import aula.projeto.spring.model.Usuario;
import aula.projeto.spring.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@EnableWebSecurity
public class WebSecurityConfig {

    private final UsuarioRepository usuarioRepository;

    public WebSecurityConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Configuração do PasswordEncoder para a segurança das senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Usando BCryptPasswordEncoder
    }

    // Configuração do UserDetailsService para carregar os usuários do banco de dados
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Buscar o usuário no banco de dados
            Usuario usuario = Optional.ofNullable(usuarioRepository.findByUsername(username)) // Envolva o retorno do repository em um Optional
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado")); // Se não encontrar, lança a exceção

            // Retorna o usuário com o nome de usuário, senha e role
            return User.builder()
                    .username(usuario.getUsername())
                    .password(usuario.getPassword()) // A senha já deve estar criptografada
                    .roles("USER")  // Aqui você pode adicionar as roles adequadas do seu usuário
                    .build();
        };
    }

    // Configuração de segurança HTTP para definir o login e as permissões
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()  // Exige autenticação para todas as rotas
                )
                .formLogin()  // Ativa o login via formulário
                .loginPage("/login")  // Página de login personalizada
                .permitAll()  // Permite o acesso à página de login
                .and()
                .logout()
                .permitAll();  // Permite o logout de qualquer URL
        return http.build();
    }

    // Certifique-se de que o AuthenticationManagerBuilder não tenha método void
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
}
