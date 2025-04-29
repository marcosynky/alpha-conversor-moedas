package aula.spring_security.repository;






import aula.spring_security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // Remova o método findByUsuario
}

