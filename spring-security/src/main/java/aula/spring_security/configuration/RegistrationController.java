package aula.spring_security.configuration;




import aula.spring_security.model.Usuario;
import aula.spring_security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RegistrationController {

    @Autowired
    private UsuarioRepository usuarioRepository;  // Aqui você está injetando o repositório

    public void buscarUsuarioComRoles(Integer usuarioId) {
        // Encontre o usuário pelo ID
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if (usuario != null) {
            // Aqui você acessa as roles do usuário
            System.out.println("Permissão do usuário: " + usuario.getRoles());
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }
}


