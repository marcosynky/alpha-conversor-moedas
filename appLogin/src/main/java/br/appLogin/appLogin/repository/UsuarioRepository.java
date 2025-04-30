package br.appLogin.appLogin.repository;

import br.appLogin.appLogin.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

      Usuario findByEmail(String email);
}
