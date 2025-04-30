package br.appLogin.appLogin.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class Usuario {

    @NotEmpty(message = "Nome não pode ser vazio")
    private String nome;

    @NotEmpty(message = "Email não pode ser vazio")
    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "Senha não pode ser vazia")
    private String senha;

    @NotEmpty(message = "Confirmar senha não pode ser vazio")
    private String confirmarSenha;

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }
}
