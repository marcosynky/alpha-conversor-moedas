package aula.projeto.spring.configuration;


import aula.projeto.spring.model.Usuario;
import aula.projeto.spring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public abstract class SecurityDataBaseService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Verifique se o usuário existe no banco
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

}
