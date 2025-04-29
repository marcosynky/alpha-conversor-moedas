package aula.projeto.spring.controller;

import aula.projeto.spring.model.Role;
import aula.projeto.spring.model.Usuario;
import aula.projeto.spring.repository.RoleRepository;
import aula.projeto.spring.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UsuarioRepository usuarioRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/create")
    public String createUsers() {
        // Criptografando a senha de Janeth
        String janethPassword = passwordEncoder.encode("1234");
        String marcoAntonioPassword = passwordEncoder.encode("123");

        // Buscando os papéis
        Role roleUser = roleRepository.findByRoleName("ROLE_USER");
        Role roleManager = roleRepository.findByRoleName("ROLE_MANAGER");

        // Criando o usuário Janeth
        Usuario janeth = new Usuario();
        janeth.setUsername("Janeth");
        janeth.setPassword(janethPassword);
        janeth.setRoles(Set.of(roleUser));
        usuarioRepository.save(janeth);

        // Criando o usuário Marco Antonio
        Usuario marcoAntonio = new Usuario();
        marcoAntonio.setUsername("Marco Antonio");
        marcoAntonio.setPassword(marcoAntonioPassword);
        marcoAntonio.setRoles(Set.of(roleManager));
        usuarioRepository.save(marcoAntonio);

        return "Usuários Janeth e Marco Antonio criados com sucesso!";
    }
}
