package aula.spring_security.configuration;

import aula.spring_security.model.Usuario;
import aula.spring_security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class SecurityDataBaseService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityDataBaseService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar o usuário pelo nome de usuário
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        // Converter as roles de String para GrantedAuthority (SimpleGrantedAuthority)
        List<SimpleGrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))  // Prefixo ROLE_ é esperado pelo Spring Security
                .collect(Collectors.toList());

        // Retorna o usuário com as roles convertidas para GrantedAuthority
        return User.withUsername(usuario.getUsername())
                .password(usuario.getPassword())  // A senha já deve estar criptografada no banco
                .authorities(authorities)  // Usando a lista de authorities convertida
                .build();
    }
}
