package br.apppostgre.apppostgrelogin.repository;

import br.apppostgre.apppostgrelogin.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Usuario findByEmailQuery(String email);
}
