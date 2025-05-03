package aula_hadlers.model;

// Classe Usuario que representa um usuário no sistema
public class Usuario {

    private Integer id;  // Atributo para armazenar o ID do usuário
    private String login;  // Atributo para armazenar o login do usuário
    private String senha;  // Atributo para armazenar a senha do usuário

    // Construtor padrão (sem parâmetros) que o Spring pode usar para criar um objeto sem precisar de parâmetros
    public Usuario() {}

    // Construtor que permite criar um objeto Usuario com valores específicos para id, login e senha
    public Usuario(Integer id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }

    // Método para obter o valor do id do usuário
    public Integer getId() {
        return id;
    }

    // Método para definir o valor do id do usuário
    public void setId(Integer id) {
        this.id = id;
    }

    // Método para obter o valor do login do usuário
    public String getLogin() {
        return login;
    }

    // Método para definir o valor do login do usuário
    public void setLogin(String login) {
        this.login = login;
    }

    // Método para obter o valor da senha do usuário
    public String getSenha() {
        return senha;
    }

    // Método para definir o valor da senha do usuário
    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Método toString que fornece uma representação em String do objeto Usuario
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';  // Exibe os valores dos atributos do objeto como uma String
    }

}