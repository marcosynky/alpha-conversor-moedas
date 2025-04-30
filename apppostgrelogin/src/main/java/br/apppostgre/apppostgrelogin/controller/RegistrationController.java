package br.apppostgre.apppostgrelogin.controller;

import br.apppostgre.apppostgrelogin.model.Usuario;
import br.apppostgre.apppostgrelogin.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class RegistrationController {
    @Autowired
    private UsuarioRepository usuarioRepository;  // Aqui você está injetando o repositório

    public void cadastrar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
}
