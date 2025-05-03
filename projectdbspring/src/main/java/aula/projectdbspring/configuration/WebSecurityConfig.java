package aula.projectdbspring.configuration;


import aula.projectdbspring.model.Usuario;
import aula.projectdbspring.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
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


@EnableWebSecurity
public class WebSecurityConfig {

    private final UsuarioRepository usuarioRepository;

    public WebSecurityConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Configuração do PasswordEncoder para criptografar senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Usando BCryptPasswordEncoder
    }

    // Configuração do UserDetailsService para buscar os usuários no banco de dados
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario usuario = usuarioRepository.findByUsername(username);  // Buscar no banco de dados
            if (usuario == null) {
                throw new UsernameNotFoundException("Usuário não encontrado");  // Lança exceção se não encontrar
            }

            // Retorna o usuário com as credenciais e roles
            return User.builder()
                    .username(usuario.getUsername())
                    .password(usuario.getPassword())  // A senha já deve estar criptografada no banco
                    .roles(usuario.getRoles().stream().map(role -> role.getRoleName()).toArray(String[]::new))  // Roles do usuário
                    .build();
        };
    }

    // Configuração do AuthenticationManager para usar o UserDetailsService
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailsService())  // Usando o UserDetailsService configurado
                .passwordEncoder(passwordEncoder());     // Configura o password encoder

        return authenticationManagerBuilder.build();  // Cria e retorna o AuthenticationManager
    }

    // Configuração de segurança HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/register").permitAll()  // Permite acesso à página de registro
                        .requestMatchers("/home").hasRole("USER")  // Requer role para acessar a página inicial
                        .requestMatchers("/admin").hasRole("ADMIN")  // Requer role para acessar a página de administração
                        .requestMatchers("/login").permitAll()  // Permite acesso à página de login
                        .requestMatchers("/logout").permitAll()  // Permite logout em qualquer URL


                )

                .logout(logout -> logout
                        .permitAll() // Permite logout em qualquer URL
                        .logoutUrl("/logout") // URL de logout personalizada
                        .clearAuthentication(true)  // Limpa a autenticação
                );
        return http.build();
    }


    }



