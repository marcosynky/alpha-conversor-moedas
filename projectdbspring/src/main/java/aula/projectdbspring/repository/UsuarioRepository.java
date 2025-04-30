package aula.projectdbspring.repository;



import aula.projectdbspring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);  // Método para buscar o usuário por nome de usuário
}
