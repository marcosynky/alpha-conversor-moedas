package aula.projectdbspring.configuration;



import aula.projectdbspring.model.Role;
import aula.projectdbspring.model.Usuario;
import aula.projectdbspring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityDataBaseService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Implementação do UserDetailsService para carregar o usuário do banco
    @Override
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar usuário no banco de dados
        Usuario usuario = Optional.ofNullable(usuarioRepository.findByUsername(username)) // Envolva o retorno do repository em um Optional
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado")); // Se não encontrar, lança a exceção

        // Validação de senha
        // Não há necessidade de fazer passwordEncoder.matches, porque a senha já deve estar criptografada no banco
        return (User) User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword()) // A senha já deve estar criptografada
                .roles(usuario.getRoles().stream().map(Role::getRoleName).toArray(String[]::new)) // Buscando roles do banco
                .build();
    }
}

